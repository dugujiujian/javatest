package com.dugu.test.service.distribute.lock.autoconfig;

import com.dugu.test.service.distribute.lock.DistributeLockManager;
import com.dugu.test.service.distribute.lock.impl.RedisDistributeLockManagerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * <pre>
 * 在application.properties中存在了对应的配置才启动
 * </pre>
 *
 * @author weipeng2k 2019年04月03日 下午14:02:57
 */
public class DistributeLockAutoConfiguration {


    @Bean("redisDistributeLockManager")
    @ConditionalOnProperty(prefix = "spring.distribute-lock.config", name = {"redis-host","redis-password"})
    public DistributeLockManager redisDistributeLockManager(
            @Value("${spring.distribute-lock.config.redis-host}") String host,
            @Value("${spring.distribute-lock.config.redis-port:6379}") Integer port,
            @Value("${spring.distribute-lock.config.redis-password}") String password,
            //链接池中创建的最大连接数
            @Value("${spring.distribute-lock.config.redis-max-total:200}") Integer maxTotal,
            //指定连接池中最大空闲连接数
            @Value("${spring.distribute-lock.config.redis-max-idle:50}") Integer maxIdle,
            @Value("${spring.distribute-lock.config.redis-connect-timeout:5000}") int connectTimeout,
            @Value("${spring.distribute-lock.config.redis-so-timeout:10000}") int soTimeout,
            @Value("${spring.distribute-lock.config.redis-max-wait-mills:200}") int maxWaitMills,
            @Value("${spring.distribute-lock.config.spin-millis:20}") int spinMillis) {

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMaxTotal(maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinEvictableIdleTimeMillis(60000);
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        poolConfig.setNumTestsPerEvictionRun(-1);
        //当资源池用尽后，调用者是否要等待，不等待会直接返回连接不足的异常，只有当值为true时，下面的maxWaitMillis才会生效。
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setMaxWaitMillis(maxWaitMills);
        JedisPool jedisPool = new JedisPool(poolConfig, host, port, connectTimeout, soTimeout, password, 0, null);
        RedisDistributeLockManagerImpl distributeLockManager = new RedisDistributeLockManagerImpl();
        distributeLockManager.setJedisPool(jedisPool);
        distributeLockManager.setSpinMills(spinMillis);

        return distributeLockManager;

    }

}

