package com.okdeer.mall.redis;

import org.apache.log4j.Logger;
/**
 * redis配置管理
 * @author Administrator
 *
 */
public class RedisBase {
    private static final Logger logger = Logger.getLogger(RedisBase.class);
    protected String collectionKey;
    public RedisBase(String collectionKey) {
        this.collectionKey = collectionKey;
    }
    /**
     * redis连接缓存池
     */
    private ShardedJedisPool jedisPool;
    /**
     * 获取redis连接
     *
     * @return conn
     */
    /**
     * 获取redis连接
     *
     * @return conn
     */
    public ShardedJedis getJedis() {
        ShardedJedis jedis = null;
        int times = 0;// 获取连接次数
        int repeats = 2;// 重复次数
        while (jedis == null) {
            try {
                times++;
                jedis = jedisPool.getResource();
            } catch (Exception e) { // 捕捉异常
                if (times <= repeats) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                    }
                } else {
                    break;
                }
            }
        }
        return jedis;
    }
    /**
     * 销毁连接缓冲池
     */
    public void destroyJedisPool() {
        if (jedisPool != null) {
            logger.info("destroy jedis pool.");
            jedisPool.destroy();
            jedisPool = null;
        }
    }
    /**
     * 释放redis连接
     *
     * @param conn
     */
    public void returnJedis(ShardedJedis jedis) {
        if (null != jedis) {
            try {
                jedisPool.returnResource(jedis);
            } catch (Exception e) {
                logger.error("return jedist to jedis pool error.", e);
            }
        }
    }
    /**
     * 设置连接池
     *
     */
    public void setJedisPool(ShardedJedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    /**
     * @return the jedisPool
     */
    public ShardedJedisPool getJedisPool() {
        return jedisPool;
    }
    protected String serialize(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteClassName);
    }
    @SuppressWarnings("unchecked")
    protected <T> T deserialize(String json) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return (T) JSON.parse(json);
    }
}

