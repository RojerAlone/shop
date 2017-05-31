package cn.cie.services.impl;

import cn.cie.entity.User;
import cn.cie.mapper.UserMapper;
import cn.cie.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by RojerAlone on 2017/5/31.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean register(User user) {
        return false;
    }

}
