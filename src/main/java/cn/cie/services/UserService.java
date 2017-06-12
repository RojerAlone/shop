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
    Result register(User user);

    /**
     * 给用户注册的邮箱发送验证码
     * @param uid
     * @return
     */
    Result sendMail(Integer uid);

    /**
     * 邮箱验证
     * @param uid
     * @param code
     * @return
     */
    Result validate(Integer uid, String code);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);

    /**
     * 登出
     * @return
     */
    Result logout(String token);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUserInfo(User user);

    /**
     * 限制账户操作
     * @param uid
     * @return
     */
    Result restrict(Integer uid);

    /**
     * 解除账户限制
     * @param uid
     * @return
     */
    Result relieve(Integer uid);

    /**
     * 删除已经过期了的验证码，验证码有效期为10分钟
     */
    void delValidatecode();

    /**
     * 删除没有验证的用户
     */
    void delNotValidateUser();

    /**
     * 删除已经过期的token
     */
    void expireToken();
}
