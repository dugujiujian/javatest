package com.dugu.test.service.distribute.lock;


/**
 * <pre>
 * 分布式锁管理服务，用户分布式锁的创建和管理
 *
 * </pre>
 *
 * @author weipeng2k
 * @date 2022-04-09 10:26 下午
 */
public interface DistributeLockManager {

    /**
     * <pre>
     * 获取资源对应的分布式锁
     *
     * 使用方式：
     *
     * <code>DistributeLock lock = distributeLockManager.getLock();</code>
     * <code>if (lock.tryLock(1, 1, TimeUnit.SECONDS)) {</code>
     * <code>    try {</code>
     * <code>        // do sth...</code>
     * <code>    } finally {</code>
     * <code>        lock.unlock();</code>
     * <code>    } </code>
     * <code>}</code>
     * </pre>
     *
     * @param resourceName 资源名称，不能为空
     * @return 分布式锁
     */
    DistributeLock getLock(String resourceName);
}