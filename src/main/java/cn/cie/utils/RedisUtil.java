package cn.cie.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/13.
 * 封装了redis操作，用protostuff进行序列化和反序列化
 * 如果要插入对象，需要调用setSchema方法指定对象的Class类型
 */
@Component
public class RedisUtil implements InitializingBean {

    private JedisPool jedisPool;

    private static final String REDIS_URL = "redis://localhost:6379/6";

    private RuntimeSchema schema;

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
    public String putObject(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = setBytes(value);
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
    public String putObjectEx(String key, Object value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = setBytes(value);
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
    public Object getObject(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[] bytes = jedis.get(key.getBytes());
            return getBytes(bytes);
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

    /**
     * 数组中添加一条数据
     * @param key
     * @param values
     * @return
     */
    public long lpushObject(String key, Object... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            byte[][] bytes = new byte[values.length][];
            int index = 0;
            for (Object value : values) {
                bytes[index] = setBytes(value);
                index++;
            }
            return jedis.lpush(key.getBytes(), bytes);
        } finally {
            jedis.close();
        }
    }

    /**
     * 获取数组中所有数据
     * @param key
     * @return
     */
    public List<Object> lall(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 0表示第一个元素，-1表示最后一个元素
            List<byte[]> bytes = jedis.lrange(key.getBytes(), 0, -1);
            List<Object> res = new ArrayList<Object>();
            for (byte[] b : bytes) {
                res.add(getBytes(b));
            }
            return res;
        } finally {
            jedis.close();
        }
    }

    /**
     * 如果要从redis中查询对象，那么必须调用这个方法设置对象的class
     * @param clazz
     */
    public void setSchema(Class clazz) {
        this.schema = RuntimeSchema.createFrom(clazz);
    }

    /**
     * 用protostuff将对象序列化为bytes
     * @param value
     * @return
     */
    private byte[] setBytes(Object value) {
        return ProtostuffIOUtil.toByteArray(value, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
    }

    /**
     * 从bytes中反序列化为对象
     * @param bytes
     * @return
     */
    private Object getBytes(byte[] bytes) {
        if (bytes != null) {
            Object object = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, object, schema);
            return object;
        } else {
            return null;
        }
    }

    /**
     * spring注入之后会自动调用这个方法
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool(REDIS_URL);
    }
}
