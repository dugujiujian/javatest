package com.dugu.test.service.distribute.lock.impl;

/**
 * @Author cihun
 * @Date 2022-04-12 1:35 下午
 */

import com.dugu.test.service.distribute.lock.DistributeLock;
import lombok.Setter;
import lombok.ToString;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author arthur 2021年03月29日 下午13:47:47
 */
@Setter
@ToString
class RedisDistributeLockSupport implements DistributeLock {

    /**
     * 资源名称，对应的Redis Key
     */
    private String resourceName;
    /**
     * 资源对应的值，当前线程需要设置的值
     */
    private String resourceValue;
    /**
     * redis api
     */
    private JedisPool jedisPool;
    /**
     * 自旋时间，毫秒数
     */
    private int spinMills;
    /**
     * 是否获取了锁
     */
    private volatile boolean locked;

    private static final String LOCK_MSG = "OK";

    @Override
    public boolean tryLock(long acquireTime, long ownTime, TimeUnit unit) {
        Objects.requireNonNull(unit, "TimeUnit is null");
        // 目标最大超时时间
        long destinationTimeMillis = System.currentTimeMillis() + unit.toMillis(acquireTime);
        boolean result = false;

        try {
            while (true) {
                // 当前系统时间
                long current = System.currentTimeMillis();
                // 时间限度外，直接退出
                if (current > destinationTimeMillis) {
                    break;
                }
                // 远程获取到资源后，返回；否则，spin
                if (lockRemoteResource(ownTime, unit)) {
                    result = true;
                    break;
                } else {
                    spinUnInterrupt();
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("acquire distribute lock got exception", ex);
        }

        return result;
    }

    @Override
    public boolean tryLockInterruptibly(long acquireTime, long ownTime, TimeUnit unit) throws InterruptedException {
        Objects.requireNonNull(unit, "TimeUnit is null");
        // 目标最大超时时间
        long destinationTimeMillis = System.currentTimeMillis() + unit.toMillis(acquireTime);
        boolean result = false;

        try {
            while (true) {
                // 当前系统时间
                long current = System.currentTimeMillis();
                // 时间限度外，直接退出
                if (current > destinationTimeMillis) {
                    break;
                }
                // 远程获取到资源后，返回；否则，spin
                if (lockRemoteResource(ownTime, unit)) {
                    result = true;
                    break;
                } else {
                    spin();
                }
            }
        } catch (InterruptedException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("acquire distribute lock got exception", ex);
        }

        return result;
    }

    @Override
    public void unlock() {
        if (locked) {
            try {
                jedisPool.getResource().del(resourceName);
                locked = false;
            } catch (Exception ex) {
                // Ignore.
            }
        }
    }

    /**
     * 锁定远端资源
     *
     * @param ownTime 占据时间
     * @param unit    时间单位
     * @return 是否锁定，true表示获取成功
     */
    private boolean lockRemoteResource(long ownTime, TimeUnit unit) {
        boolean result = false;
        SetParams setParams = new SetParams().nx().px(unit.toSeconds(ownTime));
        try (Jedis resource = jedisPool.getResource()) {
            String remoteResult = resource.set(resourceName, resourceValue, setParams);
            // 返回是OK，则锁定成功，否则锁定资源失败
            if (LOCK_MSG.equalsIgnoreCase(remoteResult)) {
                locked = true;
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 自旋等待
     */
    private void spinUnInterrupt() {
        try {
            TimeUnit.MILLISECONDS.sleep(spinMills);
        } catch (Exception ex) {
            // Ignore.
        }
    }

    /**
     * 自旋等待，响应中断
     *
     * @throws InterruptedException 中断异常
     */
    private void spin() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(spinMills);
    }

}