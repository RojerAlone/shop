package cn.cie.services.impl;

import cn.cie.entity.User;
import cn.cie.mapper.UserMapper;
import cn.cie.services.UserService;
import cn.cie.utils.MsgUtil;
import cn.cie.utils.PasswordUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by RojerAlone on 2017/5/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Result register(User user) {
        if (userMapper.selectByName(user.getUsername()) != null) {                  // 用户名已经被注册
            return Result.fail(MsgUtil.USER_USERNAME_EXISTS);
        } else if (userMapper.selectByEmail(user.getEmail()) != null) {             // 邮箱已被注册
            return Result.fail(MsgUtil.USER_EMAIL_REGISTERED);
        } else if (1 == userMapper.insert(user)) {                                  // 注册成功
            return Result.success();
        } else {                                                                      // 参数错误
            return Result.fail(MsgUtil.PARAMS_ERROR);
        }
    }

    public Result login(String username, String password) {
        User user = userMapper.selectByName(username);
        // 用户名不存在或者密码错误
        if (user == null || !user.getPassword().equals(PasswordUtil.pwd2Md5(password))) {
            return Result.fail(MsgUtil.USER_LOGIN_ERROR);
        } else {
            return Result.success();
        }
    }

    public boolean updateUserInfo(User user) {
        return 1 == userMapper.update(user);
    }

    public boolean restrict(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setStat(User.STAT_RESTRICT);
        return 1 == userMapper.update(user)
    }
}
