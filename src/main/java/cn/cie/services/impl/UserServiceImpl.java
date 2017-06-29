package cn.cie.services.impl;

import cn.cie.entity.Token;
import cn.cie.entity.User;
import cn.cie.event.EventModel;
import cn.cie.event.EventProducer;
import cn.cie.event.EventType;
import cn.cie.utils.UserHolder;
import cn.cie.entity.Validatecode;
import cn.cie.mapper.TokenMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.services.UserService;
import cn.cie.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by RojerAlone on 2017/5/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserHolder userHolder;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EventProducer eventProducer;

    @Transactional
    public Result register(User user) {
        // 验证参数是否合法
        if (StringUtils.isBlank(user.getUsername())) {
            return Result.fail(MsgCenter.EMPTY_USERNAME);
        } else if (StringUtils.isBlank(user.getNickname())) {
            return Result.fail(MsgCenter.EMPTY_NICKNAME);
        } else if (user.getNickname().length() > 10) {
            return Result.fail(MsgCenter.ERROR_NICINAME);
        } else if (StringUtils.isBlank(user.getPassword())) {
            return Result.fail(MsgCenter.EMPTY_PASSWORD);
        } else if (16 < user.getPassword().replaceAll(" ", "").length()
                || user.getPassword().replaceAll(" ", "").length() < 6) {
            return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
        } else if (StringUtils.isBlank(user.getEmail())) {
            return Result.fail(MsgCenter.EMPTY_EMAIL);
        } else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
                matcher(user.getEmail()).find() == false) {   // 判断邮箱格式是否正确
            return Result.fail(MsgCenter.ERROR_EMAIL);
        } else if (user.getPhone() == null) {
            return Result.fail(MsgCenter.EMPTY_PHONE);
        } else if (Pattern.compile("1[3|5|7|8|]\\d{9}").matcher(user.getPhone().toString()).find() == false) {  // 验证手机号码是否格式正确
            return Result.fail(MsgCenter.ERROR_PHONE);
        } else if (userMapper.selectByName(user.getUsername()) != null) {                  // 用户名已经被注册
            return Result.fail(MsgCenter.USER_USERNAME_EXISTS);
        } else if (userMapper.selectByEmail(user.getEmail()) != null) {             // 邮箱已被注册
            return Result.fail(MsgCenter.EMAIL_REGISTERED);
        }

        user.setPassword(PasswordUtil.pwd2Md5(user.getPassword().replaceAll(" ", "")));                // 加密密码
        if (1 == userMapper.insert(user)) {                                      // 注册成功
            String uuid = UUID.randomUUID().toString();
            redisUtil.putEx("validatecode_" + user.getId(), uuid, Validatecode.TIMEOUT);    // 存入redis
            eventProducer.product(new EventModel(EventType.SEND_VALIDATE_EMAIL).setExts("mail", user.getEmail()).setExts("code", uuid));
            return Result.success(user.getId());
        }
        return Result.fail(MsgCenter.ERROR);
    }

    @Transactional
    public Result sendMail(User user) {
        if (user.getStat().equals(User.STAT_OK)) {    // 用户已经验证过了
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        String uuid = UUID.randomUUID().toString();
        // 将数据存入redis中，固定时间后过期
        redisUtil.putEx("validatecode_" + user.getId(), uuid, Validatecode.TIMEOUT);
        // 将邮件发送事件添加到异步事件队列中去
        eventProducer.product(new EventModel(EventType.SEND_VALIDATE_EMAIL).setExts("mail", user.getEmail()).setExts("code", uuid));
        return Result.success();
    }

    @Transactional
    public Result validate(Integer uid, String code) {
        String uuid = redisUtil.get("validatecode_" + uid);
        if (code != null && code.length() == 36 && code.equals(uuid)) {
            User user = userHolder.getUser();
            user.setStat(User.STAT_OK);
            if (1 == userMapper.update(user)) {
                redisUtil.delete("validatecode_" + uid);        // 验证成功后删除验证码
                return Result.success();
            } else {
                return Result.fail(MsgCenter.ERROR);
            }
        }
        return Result.fail(MsgCenter.CODE_ERROR);
    }

    @Transactional
    public Result login(String username, String password, boolean remember, String ip, String device) {
        if (username == null || password == null) {
            return Result.fail(MsgCenter.EMPTY_LOGIN);
        }
        User user = userMapper.selectByName(username);
        // 用户名不存在或者密码错误或者用户已经被删除
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))
                || user.getStat().equals(User.STAT_DEL)) {
            return Result.fail(MsgCenter.ERROR_LOGIN);
        } else if (user.getStat().equals(User.STAT_RESTRICT)) {
            return Result.fail(MsgCenter.USER_RESTRICT);
        } else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Token token = new Token();
            token.setUid(user.getId());
            token.setToken(uuid);
            token.setIp(ip);
            token.setDevice(device);
            // 如果保持登陆，过期时间就为7天，否则为1天
            if (remember) {
                token.setExpiredTime(new Date(1000 * 60 * 60 * 24 * 7 + System.currentTimeMillis()));
            } else {
                token.setExpiredTime(new Date(1000 * 60 * 60 * 24 + System.currentTimeMillis()));
            }
            tokenMapper.insert(token);
            redisUtil.putEx(uuid, String.valueOf(user.getId()), 60 * 60 * 24);
            return Result.success(uuid);
        }
    }

    public Result logout(String token) {
        if (token == null || token.length() != 32) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        redisUtil.delete(token);
        tokenMapper.updateStatByToken(Token.STAT_EXPIRED, token);
        return Result.success();
    }

    public Result updateUserInfo(User user) {
        if (userHolder.getUser() == null) {
            return Result.fail(MsgCenter.USER_NOT_LOGIN);
        }
        // 验证参数是否合法
        if (StringUtils.isBlank(user.getNickname())) {
            return Result.fail(MsgCenter.EMPTY_NICKNAME);
        } else if (user.getNickname().length() > 10) {
            return Result.fail(MsgCenter.ERROR_NICINAME);
        } else if (StringUtils.isBlank(user.getEmail())) {
            return Result.fail(MsgCenter.EMPTY_EMAIL);
        } else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
                matcher(user.getEmail()).find() == false) {   // 判断邮箱格式是否正确
            return Result.fail(MsgCenter.ERROR_EMAIL);
        } else if (user.getPhone() == null) {
            return Result.fail(MsgCenter.EMPTY_PHONE);
        } else if (Pattern.compile("1[3|5|7|8|]\\d{9}").matcher(user.getPhone().toString()).find() == false) {  // 验证手机号码是否格式正确
            return Result.fail(MsgCenter.ERROR_PHONE);
        } else if (userMapper.selectByEmail(user.getEmail()) != null && !userHolder.getUser().getEmail().equals(user.getEmail())) {             // 更改邮箱同时邮箱已被注册
            return Result.fail(MsgCenter.EMAIL_REGISTERED);
        }
        user.setId(userHolder.getUser().getId());
        user.setPassword(null);
        user.setStat(null);
        if (1 == userMapper.update(user)) {
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result updatePassword(String password) {
        if (StringUtils.isBlank(password)) {
            return Result.fail(MsgCenter.EMPTY_PASSWORD);
        } else if (16 < password.replaceAll(" ", "").length()
                || password.replaceAll(" ", "").length() < 6) {
            return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
        }
        User user = userHolder.getUser();
        user.setPassword(PasswordUtil.pwd2Md5(password.replaceAll(" ", "")));
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    public Result forgetPassword(String password, String email, String code) {
        User user = null;
        if (StringUtils.isBlank(email)) {
            return Result.fail(MsgCenter.EMPTY_EMAIL);
        } else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
                matcher(email).find() == false || (user = userMapper.selectByEmail(email)) == null) {   // 判断邮箱格式以及两次邮箱是否一致
            return Result.fail(MsgCenter.ERROR_EMAIL);
        } else if (StringUtils.isBlank(password)) {
            return Result.fail(MsgCenter.EMPTY_PASSWORD);
        } else if (16 < password.replaceAll(" ", "").length()
                || password.replaceAll(" ", "").length() < 6) {
            return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
        }
        String uuid = redisUtil.get(email);
        if (code != null && code.length() == 36 && code.equals(uuid)) {
            user.setPassword(PasswordUtil.pwd2Md5(password.replaceAll(" ", "")));
            if (1 == userMapper.update(user)) {
                redisUtil.delete(email);        // 验证成功后删除验证码
                return Result.success(user.getUsername());
            } else {
                return Result.fail(MsgCenter.ERROR);
            }
        }
        return Result.fail(MsgCenter.CODE_ERROR);
    }

    public Result sendFetchPwdMail(String email) {
        if (StringUtils.isBlank(email)) {
            return Result.fail(MsgCenter.EMPTY_EMAIL);
        } else if (Pattern.compile("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").
                matcher(email).find() == false) {   // 判断邮箱格式是否正确
            return Result.fail(MsgCenter.ERROR_EMAIL);
        }
        User user = userMapper.selectByEmail(email);
        if (user == null) {
            return Result.fail(MsgCenter.EMAIL_NOT_REGISTERED);
        }
        String uuid = UUID.randomUUID().toString();
        // 将数据存入redis中，固定时间后过期
        redisUtil.putEx(email, uuid, Validatecode.TIMEOUT);
        eventProducer.product(new EventModel(EventType.SEND_FIND_PWD_EMAIL).setExts("mail", user.getEmail()).setExts("code", uuid));
        return Result.success();
    }

    public void delNotValidateUser() {
        List<User> users = userMapper.selectByStat(User.STAT_NOT_VALIDATE);
        Date date = new Date();
        date.setTime(date.getTime() - 1000 * 60 * 60);  // 删除一小时前注册但未验证的用户
        for (User user : users) {
            if (user.getCtime().before(date)) {
                userMapper.deleteById(user.getId());
            }
        }
    }

    public void expireToken() {
        Date date = new Date();
        tokenMapper.updateStatByDate(date, Token.STAT_EXPIRED);
    }
}
