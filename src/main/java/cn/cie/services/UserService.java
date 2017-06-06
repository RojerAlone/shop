package cn.cie.services;

import cn.cie.entity.User;
import cn.cie.utils.Result;

/**
 * Created by RojerAlone on 2017/5/31.
 */
public interface UserService {

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register(User user);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Result login(String username, String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUserInfo(User user);

    /**
     * 限制账户操作
     * @param uid
     * @return
     */
    public boolean restrict(Integer uid);

}
