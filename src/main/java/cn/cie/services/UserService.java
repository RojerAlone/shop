package cn.cie.services;

import cn.cie.entity.User;
import cn.cie.common.utils.Result;

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
     * 给用户注册的邮箱发送验证码
     * @param uid
     * @return
     */
    public Result sendMail(Integer uid);

    /**
     * 邮箱验证
     * @param uid
     * @param code
     * @return
     */
    public Result validate(Integer uid, String code);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Result<User> login(String username, String password);

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
    public Result restrict(Integer uid);

    /**
     * 解除账户限制
     * @param uid
     * @return
     */
    public Result relieve(Integer uid);

}
