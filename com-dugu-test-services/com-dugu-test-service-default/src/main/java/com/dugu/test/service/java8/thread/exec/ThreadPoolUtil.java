package com.dugu.test.service.java8.thread.exec;

import com.google.common.collect.Queues;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author cihun
 * @Date 2022-04-13 7:40 下午
 */
public class ThreadPoolUtil {

    private static ThreadPoolUtil INSTANCE = new ThreadPoolUtil();
    /**
     * 线程池核心线程倍数
     */
    Integer THREAD_POOL_CORE = 4;
    /**
     * 线程池最大核心线程倍数
     */
    Integer THREAD_POOL_MAX = 12;

//    private int coreThreadNum = Runtime.getRuntime().availableProcessors() * THREAD_POOL_CORE + 1;
//    private int maxThreadNum = Runtime.getRuntime().availableProcessors() * THREAD_POOL_MAX + 1;

    private int coreThreadNum = 2 * THREAD_POOL_CORE + 1;
    private int maxThreadNum = 2 * THREAD_POOL_MAX + 1;


    /**
     * 空构造
     */
    ThreadPoolUtil() {

    }

    /**
     * 饿汉实例
     *
     * @return
     */
    public static ThreadPoolUtil getInstance() {
        return INSTANCE;
    }

    /**
     * <pre>
     * 异步执行的线程池大小
     * corePoolSize：核心线程数量，规定线程池有几个线程(worker)在运行。
     * maximumPoolSize：线程最大线程数；当workQueue满了,不能添加任务的时候，这个参数才会生效。规定线程池最多只能有多少个线程(worker)在执行。
     * workQueue：阻塞队列，存储等待执行的任务，很重要，会对线程池运行过程产生重大影响
     *      当我们提交一个新的任务到线程池，线程池会根据当前池中正在运行的线程数量来决定该任务的处理方式。处理方式有三种：
     *      1、直接切换（SynchronusQueue）
     *      2、无界队列（LinkedBlockingQueue）能够创建的最大线程数为corePoolSize,这时maximumPoolSize就不会起作用了。
     *         当线程池中所有的核心线程都是运行状态的时候，新的任务提交就会放入等待队列中。
     *      3、有界队列（ArrayBlockingQueue）最大maximumPoolSize，能够降低资源消耗，但是这种方式使得线程池对线程调度变的更困难。
     *         因为线程池与队列容量都是有限的。所以想让线程池的吞吐率和处理任务达到一个合理的范围，又想使我们的线程调度相对简单，并且还尽可能降低资源的消耗，我们就需要合理的限制这两个数量
     *      分配技巧： [如果想降低资源的消耗包括降低cpu使用率、操作系统资源的消耗、上下文切换的开销等等，可以设置一个较大的队列容量和较小的线程池容量，这样会降低线程池的吞吐量。如果我们提交的任务经常发生阻塞，我们可以调整maximumPoolSize。如果我们的队列容量较小，我们需要把线程池大小设置的大一些，这样cpu的使用率相对来说会高一些。但是如果线程池的容量设置的过大，提高任务的数量过多的时候，并发量会增加，那么线程之间的调度就是一个需要考虑的问题。这样反而可能会降低处理任务的吞吐量。]
     * keepAliveTime：线程没有任务执行时最多保持多久时间终止（当线程中的线程数量大于corePoolSize的时候，如果这时没有新的任务提交核心线程外的线程不会立即销毁，而是等待，直到超过keepAliveTime）
     * unit：keepAliveTime的时间单位
     * threadFactory：线程工厂，用来创建线程，有一个默认的工场来创建线程，这样新创建出来的线程有相同的优先级，是非守护线程、设置好了名称）
     * rejectHandler： 当workQueue已经满了，并且线程池线程数已经达到maximumPoolSize，将执行拒绝策略。当拒绝处理任务时(阻塞队列满)的策略（AbortPolicy默认策略直接抛出异常、CallerRunsPolicy用调用者所在的线程执行任务、DiscardOldestPolicy丢弃队列中最靠前的任务并执行当前任务、DiscardPolicy直接丢弃当前任务）
     * </pre>
     */
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            coreThreadNum,
            maxThreadNum,
            0L,
            TimeUnit.SECONDS,
            Queues.newLinkedBlockingDeque(2000),
            new ThreadFactoryBuilder().setNameFormat("dugu-task-%d").build());


    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

}
