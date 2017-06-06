package cn.cie.services;

import cn.cie.entity.User;

/**
 * Created by RojerAlone on 2017/5/31.
 */
public interface UserService {

    public boolean register(User user);

    public boolean login(String username, String password);

    public boolean updateUserInfo(User user);

    public boolean restrict(Integer uid);

}
