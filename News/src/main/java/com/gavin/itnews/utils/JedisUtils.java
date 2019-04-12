package com.gavin.itnews.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Gavin
 * on 2019/4/12 15:35
 */
public class JedisUtils {
    static JedisPool jedisPool  = new JedisPool();

    public static Jedis getJedisFromPool(){
        Jedis resource = jedisPool.getResource();
        return resource;
    }
}
