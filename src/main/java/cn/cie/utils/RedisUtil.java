package cn.cie.utils;

import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.JedisPool;

/**
 * Created by RojerAlone on 2017/6/13.
 */
public class RedisUtil implements InitializingBean {

    private JedisPool jedisPool;

    private static final String REDIS_URL = "redis://localhost:6379/6";

    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool(REDIS_URL);
    }
}
