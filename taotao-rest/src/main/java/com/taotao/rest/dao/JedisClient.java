package com.taotao.rest.dao;

public interface JedisClient {
    String get(String key);
    String key(String key, String value);
    String hget(String hkey, String key);
    Long hset(String hkey, String key, String value);
    long incr(String key);
    long expire(String key, int second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);
}
