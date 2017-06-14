package cn.cie.utils;

import cn.cie.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by RojerAlone on 2017/6/14.
 */
public class RedisUtilTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private RedisUtil redis = new RedisUtil();

    @Test
    public void put() throws Exception {
        redis.afterPropertiesSet();
        logger.info(redis.put("key", "value"));
    }

    @Test
    public void putEx() throws Exception {
    }

    @Test
    public void get() throws Exception {
        redis.afterPropertiesSet();
        logger.info(redis.get("key"));
    }

    @Test
    public void putObject() throws Exception {
        User user = new User();
        user.setNickname("rojeralone");
        user.setPassword("alsdjflasdhflsdahnfklnsdaf");
        user.setPhone(18392566666L);
        redis.afterPropertiesSet();
        redis.setSchema(User.class);
        logger.info(redis.putObject("alone", user));
    }

    @Test
    public void putObjectEx() throws Exception {
    }

    @Test
    public void getObject() throws Exception {
        redis.afterPropertiesSet();
        redis.setSchema(User.class);
        User user = (User) redis.getObject("alone");
        logger.info("user={}", user);
    }

    @Test
    public void delete() throws Exception {
    }

}