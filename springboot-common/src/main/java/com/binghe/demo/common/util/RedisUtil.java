package com.binghe.demo.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import com.alibaba.fastjson.JSON;

/**
 * redis工具
 * @author dongsw
 *
 */
@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取string类型值
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 获取object值
     * @param key
     * @param tClass 要获取的值的类型
     * @return
     */
    public <T> T getObject(String key, Class<T> tClass) {
        Jedis jedis = null;
        T result = null;
        try {
            jedis = jedisPool.getResource();
            String jsonResult = jedis.get(key);
            if (StringUtils.isNotBlank(jsonResult)) {
                result = JSON.parseObject(jsonResult, tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 获取list
     * @param key
     * @param tClass list元素的范型值
     * @return
     */
    public <T> List<T> getObjectArrays(String key, Class<T> tClass) {
        Jedis jedis = null;
        List<T> result = null;
        try {
            jedis = jedisPool.getResource();
            String jsonResult = jedis.get(key);
            if (StringUtils.isNotBlank(jsonResult)) {
                result = JSON.parseArray(jsonResult, tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    /**
     * 保存string值
     * @param key
     * @param val
     */
    public void save(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 保存object
     * @param key
     * @param val
     */
    public void saveObject(String key, Object val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, JSON.toJSONString(val));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 
     * @param key
     * @param field
     * @param val
     */
    public void setMap(String key, String field, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getFromMap(String key, String field) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public Map<String, String> getMap(String key) {
        Jedis jedis = null;
        Map<String, String> result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public Long delMapField(String key, String filed) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hdel(key, filed);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0L;
    }

    public Set<String> getKeys(String keyPattern) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.keys(keyPattern);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }


    public Set<String> getMapKeys(String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public boolean exists(String key) {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public boolean hexists(String key, String field) {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hexists(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }
    public void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    // 获取set集合中的值
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return null;
    }

    // 获取set集合中的元素个数
    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return 0;
    }

    //判断值是否是set的member
    public boolean sismember(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return false;
    }
    //移除一个值
    public long srem(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return 0;
    }
    // 增加一个值
    public long sadd(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return 0;
    }

    // 自增1
    public long incr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return -1;
    }

    // 自增increment
    public long incrBy(String key, long increment) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incrBy(key, increment);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return -1;
    }

    // 自减1
    public long decr(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return -1;
    }

    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return -1;
    }

    public String lpop(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public long llen(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0L;
    }

    public void ltrim(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.ltrim(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
    }

    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return new ArrayList<>();
    }

    public String lindex(String key, long index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lindex(key, index);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return null;
    }

    public String lset(String key, long index, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lset(key, index, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();

            }
        }
        return null;
    }

    public void expire(String key, int secs) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, secs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.publish(channel, message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
    public Map<String, String> pipelineGet(List<String> keys, String keyPre) {
        Map<String, String> result = new LinkedHashMap<>();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            Map<String, Response<String>> responseMap = new HashMap<>();
            if (keys != null) {
                for (String key : keys) {
                    Response<String> response = pipeline.get(StringUtils.isNotBlank(keyPre) ? keyPre + key : key);
                    responseMap.put(key, response);
                }
            }
            pipeline.sync();
            if (responseMap.size() > 0) {
                for (Map.Entry<String, Response<String>> entry : responseMap.entrySet()) {
                    String value = null;
                    Response<String> response = entry.getValue();
                    if (response != null) {
                        value = response.get();
                    }
                    result.put(entry.getKey(), value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public <T> Map<String, T> pipelineGetObjects(List<String> keys, String keyPre, Class<T> clazz) {
        Map<String, T> result = new LinkedHashMap<>();
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            Map<String, Response<String>> responseMap = new HashMap<>();
            if (keys != null) {
                for (String key : keys) {
                    Response<String> response = pipeline.get(StringUtils.isNotBlank(keyPre) ? keyPre + key : key);
                    responseMap.put(key, response);
                }
            }
            pipeline.sync();
            if (responseMap.size() > 0) {
                for (Map.Entry<String, Response<String>> entry : responseMap.entrySet()) {

                    Response<String> response = entry.getValue();
                    T t = null;
                    if (response != null) {
                        String value = response.get();
                        if (StringUtils.isNotBlank(value)) {
                            t = JSON.parseObject(value, clazz);
                        }
                    }
                    result.put(entry.getKey(), t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public long ttlSecs(String key) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPool.getResource();
            result = jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return new HashSet<>();
    }

}

