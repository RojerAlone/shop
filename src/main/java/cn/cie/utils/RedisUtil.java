package cn.cie.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.InitializingBean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by RojerAlone on 2017/6/13.
 */
public class RedisUtil<T> implements InitializingBean {

    protected Class<T> clazz;

    private JedisPool jedisPool;

    private static final String REDIS_URL = "redis://localhost:6379/6";

    private RuntimeSchema schema;

//    private T clazz;

    @SuppressWarnings("unchecked")
    public RedisUtil() {
        @SuppressWarnings("rawtypes")
        Class clazz = getClass();

//        while (clazz != Object.class) {
            Type t = clazz.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
                if (args[0] instanceof Class) {
                    this.clazz = (Class<T>) args[0];
                    System.out.println(this.clazz);
//                    break;
                }
            }
//            clazz = clazz.getSuperclass();
//        }
        System.out.println(clazz);
    }

    /**
     * 存放一条数据
     * @param key
     * @param value
     * @return
     */
    public String put(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * 存放一条定时过期的数据
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public String putEx(String key, String value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, timeout, value);
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    /**
     * 存放一个对象
     * @param key
     * @param value
     * @return
     */
    public String putObject(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = ProtostuffIOUtil.toByteArray(value, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            return  jedis.set(key.getBytes(), bytes);
        } finally {
            jedis.close();
        }
    }

    /**
     * 存放一个定时过期的对象
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public String putObjectEx(String key, T value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = ProtostuffIOUtil.toByteArray(value, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            return  jedis.setex(key.getBytes(), timeout, bytes);
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据key获取对应的对象
     * @param key
     * @return
     */
    public T getObject(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                T object = (T)schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, object, schema);
                return object;
            } else {
                return null;
            }
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据key删除数据
     * @param key
     * @return
     */
    public long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    public void afterPropertiesSet() throws Exception {
//        RedisUtil<T> obj = new RedisUtil<T>();

        System.out.println(this.clazz);
        jedisPool = new JedisPool(REDIS_URL);
        schema = RuntimeSchema.createFrom(clazz);
    }
}
