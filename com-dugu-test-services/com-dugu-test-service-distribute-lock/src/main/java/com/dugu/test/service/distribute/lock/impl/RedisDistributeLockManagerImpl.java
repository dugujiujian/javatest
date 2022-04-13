package com.dugu.test.service.distribute.lock.impl;

import com.dugu.test.service.distribute.lock.DistributeLock;
import com.dugu.test.service.distribute.lock.DistributeLockManager;
import redis.clients.jedis.JedisPool;

import java.util.Objects;
import java.util.UUID;

/**
 * @author arthur
 */
public class RedisDistributeLockManagerImpl implements DistributeLockManager {

    /**
     * redis api
     */
    private JedisPool jedisPool;
    /**
     * 自旋时间，毫秒数
     */
    private int spinMills;

    @Override
    public DistributeLock getLock(String resourceName) {
        Objects.requireNonNull(resourceName, "resourceName is null");

        RedisDistributeLockSupport distributeLockSupport = new RedisDistributeLockSupport();
        distributeLockSupport.setJedisPool(jedisPool);
        distributeLockSupport.setResourceName(resourceName);
        distributeLockSupport.setResourceValue(UUID.randomUUID().toString());
        distributeLockSupport.setSpinMills(spinMills);

        return distributeLockSupport;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void setSpinMills(int spinMills) {
        this.spinMills = spinMills;
    }
}