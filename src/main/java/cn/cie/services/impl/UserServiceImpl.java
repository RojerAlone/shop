package cn.cie.services.impl;

import cn.cie.entity.User;
import cn.cie.entity.Validatecode;
import cn.cie.mapper.UserMapper;
import cn.cie.mapper.ValidatecodeMapper;
import cn.cie.services.UserService;
import cn.cie.common.utils.MailUtil;
import cn.cie.common.utils.MsgCenter;
import cn.cie.common.utils.PasswordUtil;
import cn.cie.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by RojerAlone on 2017/5/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ValidatecodeMapper codeMapper;

    @Transactional
    public Result register(User user) {
        if (userMapper.selectByName(user.getUsername()) != null) {                  // 用户名已经被注册
            return Result.fail(MsgCenter.USER_USERNAME_EXISTS);
        } else if (userMapper.selectByEmail(user.getEmail()) != null) {             // 邮箱已被注册
            return Result.fail(MsgCenter.USER_EMAIL_REGISTERED);
        } else if (1 == userMapper.insert(user)) {                                  // 注册成功
            Validatecode coder = new Validatecode();
            String code = UUID.randomUUID().toString();
            coder.setUid(user.getId());
            coder.setCode(code);
            codeMapper.insert(coder);                                               // 数据库中存入验证码
            MailUtil.sendValidateMail(user.getEmail(), code);                        // 发送验证邮件
            return Result.success();
        } else {                                                                      // 参数错误
            return Result.fail(MsgCenter.PARAMS_ERROR);
        }
    }

    @Transactional
    public Result validate(Integer uid, String code) {
        String str = codeMapper.selectByUid(uid);
        if (str != null && str.length() == 36 && code.equals(str)) {
            User user = new User();
            user.setId(uid);
            user.setStat(User.STAT_OK);
            if (1 == userMapper.update(user)) {
                codeMapper.delete(uid);     // 验证成功后删除验证码
                return Result.success();
            } else {
                return Result.fail(MsgCenter.ERROR);
            }
        } else {
            return Result.fail(MsgCenter.USER_EMAIL_CODE_ERROR);
        }
    }

    public Result<User> login(String username, String password) {
        User user = userMapper.selectByName(username);
        // 用户名不存在或者密码错误
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))) {
            return Result.fail(MsgCenter.USER_LOGIN_ERROR);
        } else {
            user.setPassword(null);     // 密码设为空后返回
            return Result.success(user);
        }
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
}
