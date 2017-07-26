package cn.cie.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RojerAlone on 2017/6/13.
 * 封装了redis操作，用protostuff进行序列化和反序列化
 * 如果要插入对象，需要传入插入对象的Class类型
 */
@Component
public class RedisUtil<T> implements InitializingBean {

    private JedisPool jedisPool;

    private static final String REDIS_URL = "redis://localhost:6379/6";

    public static final String EVERYDAY = "everyday";

    public static final String KINDS = "kinds";

    public static final String NEWESTGAME = "newestgame";

    public static final String PRE_UP_GAMES = "preupgames";

    /**
     * 存放一条数据
     *
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
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 存放一条定时过期的数据
     *
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
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 存放一个对象
     *
     * @param key
     * @param value
     * @return
     */
    public String putObject(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, JSON.toJSONString(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 存放一个定时过期的对象
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public String putObjectEx(String key, T value, int timeout) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.setex(key, timeout, JSON.toJSONString(value));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key获取对应的对象
     *
     * @param key
     * @return
     */
    public T getObject(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return (T) JSON.parseObject(jedis.get(key), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key删除数据
     *
     * @param key
     * @return
     */
    public long delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从队列头部出队一个元素，如果没有，则会阻塞 timeout 秒后返回null
     * 如果 timeout 为0，那么会一直阻塞直到有元素
     *
     * @param timeout 阻塞的时间，单位为秒
     * @param key
     * @param clazz
     * @return
     */
    public T blpopObject(int timeout, String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            List<String> list = jedis.blpop(timeout, key);
            return (T) JSON.parseObject(list.get(0), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 从队列左边出队一个元素
     *
     * @param key
     * @param clazz
     * @return
     */
    public T lpopObject(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return (T) JSON.parseObject(jedis.lpop(key), clazz);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 向列表尾部添加数据
     *
     * @param key
     * @param values
     * @return
     */
    public long rpushObject(String key, Class clazz, Object... values) {
        if (values == null || values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            return jedis.rpush(key, jsonStrs);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 向列表尾部添加某个时间点删除的数据
     *
     * @param key
     * @param time   unix时间戳
     * @param values
     * @return
     */
    public long rpushObjectExAtTime(String key, Class clazz, long time, Object... values) {
        if (values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            long res = jedis.rpush(key, jsonStrs);
            jedis.expireAt(key.getBytes(), time);      // 手动设置过期时间
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 在列表尾部添加一个定期删除的数据
     *
     * @param key
     * @param clazz
     * @param timeout
     * @param values
     * @return
     */
    public long rpushObjectEx(String key, Class clazz, int timeout, Object... values) {
        if (values.length == 0) {
            return 0;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String[] jsonStrs = new String[values.length];
            int index = 0;
            for (Object value : values) {
                jsonStrs[index] = JSON.toJSONString(value);
                ++index;
            }
            long res = jedis.rpush(key, jsonStrs);
            jedis.expire(key, timeout);
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取列表中所有数据,ruguo
     *
     * @param key
     * @return
     */
    public List<T> lall(String key, Class clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 0表示第一个元素，-1表示最后一个元素
            List<String> list = jedis.lrange(key, 0, -1);
            List<T> res = new ArrayList<T>();
            if (list == null || list.size() == 0) {
                return res;
            }
            for (String str : list) {
                res.add((T) JSON.parseObject(str, clazz));
            }
            return res;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * spring注入之后会自动调用这个方法
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool(REDIS_URL);
    }
}
