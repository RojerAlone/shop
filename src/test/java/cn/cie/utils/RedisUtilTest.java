package cn.cie.utils;

import cn.cie.entity.Kind;
import cn.cie.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by RojerAlone on 2017/6/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring-dao.xml", "classpath:spring-service.xml"})
public class RedisUtilTest {

    private final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisUtil redis;

    @Test
    public void put() throws Exception {
        logger.info(redis.put("key", "value"));
    }

    @Test
    public void putEx() throws Exception {
        logger.info(redis.putEx("ex", "exvalue", 10));
    }

    @Test
    public void get() throws Exception {
        logger.info(redis.get("key"));
        logger.info(redis.get("ex"));
//        logger.info(redis.get("kinds"));
    }

    @Test
    public void putObject() throws Exception {
        User user = new User();
        user.setNickname("rojeralone");
        user.setPassword("alsdjflasdhflsdahnfklnsdaf");
        user.setPhone(18392566666L);
        redis.setSchema(User.class);
        logger.info(redis.putObject("alone", user));
    }

    @Test
    public void putObjectEx() throws Exception {
    }

    @Test
    public void getObject() throws Exception {
        redis.setSchema(User.class);
        User user = (User) redis.getObject("alone");
        logger.info("user={}", user);
    }

    @Test
    public void delete() throws Exception {
        logger.info("delete : " + redis.delete("ex"));
    }

    @Test
    public void lall() throws Exception {
        redis.setSchema(Kind.class);
        logger.info("kinds={}" + redis.lall("kinds"));
    }

}