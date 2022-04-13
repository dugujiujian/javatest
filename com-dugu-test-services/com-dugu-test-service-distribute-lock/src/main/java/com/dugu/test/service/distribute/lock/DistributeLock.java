package com.dugu.test.service.distribute.lock;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 分布式的锁
 * 对于锁，需要兑现两个含义：
 * <li>可见性</li>
 * <li>排他性</li>
 *
 * 对于使用远程存储的锁而言，可见性由对方存储的CAS来保证，排他性也是如此
 *
 * 在可用性上，分布式锁需要考虑获取以及加锁后的超时问题。
 *
 * <li>获取超时</li>
 * <li>加锁超时</li>
 *
 * 1. 对于获取超时，当客户端进行锁定资源时（有远程通信），有超时限制，当限制到达时，表示对资源的获取失败；
 * 2. 对于加锁超时，当客户端占据了资源后，开始进行处理，但是对于资源的占有还是需要有超时上限，避免出现资源的死锁。
 *
 * </pre>
 *
 * @author weipeng2k 2019年09月05日 下午19:46:04
 */
public interface DistributeLock {

    /**
     * <pre>
     * 尝试等待acquireTime的时间进行锁的获取，如果时间范围内无法获取到则返回
     * 如果能够锁定资源，则会占有资源一段时间，最长ownTime
     * 当ownTime到达，将会被其他的线程所占据
     *
     * 该方法确保分布式环境下的资源获取，在time时间内获取资源，并占据资源ownTime
     *
     * </pre>
     *
     * @param acquireTime 获取锁的最大超时时间
     * @param ownTime     占据资源的最长时间
     * @param unit        时间单位
     * @return 是否锁定成功，如果返回false表示锁定失败
     */
    boolean tryLock(long acquireTime, long ownTime, TimeUnit unit);

    /**
     * <pre>
     * 尝试等待acquireTime的时间进行锁的获取，如果时间范围内无法获取到则返回，该方法响应中断
     *
     * </pre>
     *
     * @param acquireTime 获取锁的最大超时时间
     * @param ownTime     占据资源的最长时间
     * @param unit        时间单位
     * @return 是否锁定成功，如果返回false表示锁定失败
     */
    boolean tryLockInterruptibly(long acquireTime, long ownTime, TimeUnit unit) throws InterruptedException;

    /**
     * 解锁，释放锁资源
     */
    void unlock();


}
