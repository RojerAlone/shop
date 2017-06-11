package cn.cie.services;

import cn.cie.utils.Result;
import cn.cie.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by RojerAlone on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class UserServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Test
    public void register() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123qwe");
        user.setNickname("管理员");
        user.setEmail("1091165843@qq.com");
        user.setPhone(18392566666L);
        Result result = userService.register(user);
        try {
            if (result.isSuccess()) {
                logger.info("注册成功");
            } else {
                logger.info("注册失败: " + result.getMsg());
            }
        } catch (Exception e) {
            logger.error("出错", e);
        }
    }

    @Test
    public void validate() throws Exception {
        int uid = 1;
        String code = "0c09c363-5919-4da1-93fa-e599491725ba";
        try {
            Result res = userService.validate(uid, code);
            if (res.isSuccess()) {
                logger.info("验证成功");
            } else {
                logger.info("验证失败: " + res.getMsg());
            }
        } catch (Exception e) {
            logger.error("出错", e);
        }
    }

    @Test
    public void login() throws Exception {
    }

    @Test
    public void updateUserInfo() throws Exception {
    }

    @Test
    public void restrict() throws Exception {
    }

    @Test
    public void relieve() throws Exception {
    }

}