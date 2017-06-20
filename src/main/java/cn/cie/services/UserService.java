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
     * @param user
     * @return
     */
    Result sendMail(User user);

    /**
     * 邮箱验证
     * @param uid
     * @param code
     * @return
     */
    Result validate(Integer uid, String code);

    /**
     * 登录
     * 登陆时如果选择了“记住我”选项，那么token保持7天，否则保存1天
     * 登陆成功，将token存在数据库中记录，同时存入缓存，过期时间为1天
     * 每次请求时拦截器先从缓存中查找，如果没有再去数据库中查找
     * @param username  用户名
     * @param password  密码
     * @param remember  是否保持登陆（token生存周期为7天）
     * @return
     */
    Result login(String username, String password, boolean remember, String ip, String device);

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
    Result updateUserInfo(User user);

    Result updatePassword(String password, String code);

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
