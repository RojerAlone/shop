package cn.cie.services.impl;

import cn.cie.entity.Token;
import cn.cie.entity.User;
import cn.cie.utils.UserHolder;
import cn.cie.entity.Validatecode;
import cn.cie.mapper.TokenMapper;
import cn.cie.mapper.UserMapper;
import cn.cie.mapper.ValidatecodeMapper;
import cn.cie.services.UserService;
import cn.cie.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by RojerAlone on 2017/5/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ValidatecodeMapper codeMapper;
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private UserHolder userHolder;

    @Transactional
    public Result register(User user) {
        // 验证参数是否合法
        if (user.getUsername() == null) {
            return Result.fail(MsgCenter.EMPTY_USERNAME);
        } else if (user.getPassword() == null) {
            return Result.fail(MsgCenter.EMPTY_PASSWORD);
        } else if (16 < user.getPassword().length() || user.getPassword().length() < 6) {
            return Result.fail(MsgCenter.ERROR_PASSWORD_FORMAT);
        } else if (user.getEmail() == null) {
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

        user.setPassword(PasswordUtil.pwd2Md5(user.getPassword()));                // 加密密码
        if (1 == userMapper.insert(user)) {                                      // 注册成功
            String uuid = UUID.randomUUID().toString();
            Validatecode code = new Validatecode();
            code.setUid(user.getId());
            code.setCode(uuid);
            codeMapper.insert(code);    // 数据库中存入验证码
            MailUtil.sendValidateMail(user.getEmail(), uuid); // 发送验证邮件
            return Result.success(user.getId());
        }
        return Result.fail(MsgCenter.ERROR);
    }

    @Transactional
    public Result sendMail(Integer uid) {
        User user = userMapper.selectById(uid);
        if (user == null) {     // 找不到用户
            return Result.fail(MsgCenter.USER_NOT_FOUND);
        } else if (user.getStat() == User.STAT_OK) {    // 用户已经验证过了
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        Validatecode code = new Validatecode();
        String uuid = UUID.randomUUID().toString();
        code.setUid(uid);
        code.setCode(uuid);
        codeMapper.insert(code);    // 数据库中存入验证码
        MailUtil.sendValidateMail(user.getEmail(), uuid); // 发送验证邮件
        return Result.success();
    }

    @Transactional
    public Result validate(Integer uid, String code) {
        User user = userMapper.selectById(uid);
        if (user == null) {     // 找不到用户
            return Result.fail(MsgCenter.USER_NOT_FOUND);
        } else if (user.getStat() == User.STAT_OK) {    // 用户已经验证过了
            return Result.fail(MsgCenter.USER_VALIDATED);
        }
        List<String> strs = codeMapper.selectByUid(uid);
        for (String str : strs) {
            if (str != null && str.length() == 36 && code.equals(str)) {
                user.setStat(User.STAT_OK);
                if (1 == userMapper.update(user)) {
                    codeMapper.delete(uid);     // 验证成功后删除验证码
                    return Result.success();
                } else {
                    return Result.fail(MsgCenter.ERROR);
                }
            }
        }
        return Result.fail(MsgCenter.CODE_ERROR);
    }

    @Transactional
    public Result login(String username, String password, String ip, String device) {
        User user = userMapper.selectByName(username);
        // 用户名不存在或者密码错误
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))) {
            return Result.fail(MsgCenter.ERROR_LOGIN);
        } else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            Token token = new Token();
            token.setUid(user.getId());
            token.setExpiredTime(new Date(1000 * 60 * 60 * 24 + System.currentTimeMillis()));
            token.setToken(uuid);
            token.setIp(ip);
            token.setDevice(device);
            tokenMapper.insert(token);
            return Result.success(uuid);
        }
    }

    public Result logout(String token) {
        if (token == null || token.length() != 32) {
            return Result.success();
        }
        tokenMapper.updateStatByToken(Token.STAT_EXPIRED, token);
        return Result.success();
    }

    public boolean updateUserInfo(User user) {
        return 1 == userMapper.update(user);
    }

    public Result restrict(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_RESTRICT);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public Result relieve(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_OK);
        if (1 == userMapper.update(user)) {
            return Result.success();
        } else {
            return Result.fail("");
        }
    }

    public void delValidatecode() {
        List<Validatecode> codes = codeMapper.selectAll();
        Date date = new Date();
        date.setTime(date.getTime() - 1000 * 60 * 10);
        for (Validatecode code : codes) {
            if (code.getCtime().before(date)) {
                codeMapper.delByCode(code);
            }
        }
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
