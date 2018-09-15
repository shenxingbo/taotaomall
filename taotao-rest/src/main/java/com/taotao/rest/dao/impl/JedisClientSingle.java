package com.taotao.rest.dao.impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        jedis.close();
        return str;
    }

    @Override
    public String key(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key,value);
        jedis.close();
        return str;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hget(hkey, key);
        jedis.close();
        return str;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long str = jedis.hset(hkey, key, value);
        jedis.close();
        return str;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        long str = jedis.incr(key);
        jedis.close();
        return str;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long str = jedis.expire(key, second);
        jedis.close();
        return str;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long str = jedis.ttl(key);
        jedis.close();
        return str;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long str = jedis.del(key);
        jedis.close();
        return str;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long str = jedis.hdel(hkey, key);
        jedis.close();
        return str;
    }


}
