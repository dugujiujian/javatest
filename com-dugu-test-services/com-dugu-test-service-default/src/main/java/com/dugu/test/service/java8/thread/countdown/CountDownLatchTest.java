package com.dugu.test.service.java8.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cihun
 * @date 2023-09-10 21:14
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 让2个线程去等待3个三个工作线程执行完成
        CountDownLatch c = new CountDownLatch(3);

        // 2 个等待线程
        WaitThread waitThread1 = new WaitThread("wait-thread-1", c);
        WaitThread waitThread2 = new WaitThread("wait-thread-2", c);

        // 3个工作线程
        Worker worker1 = new Worker("worker-thread-1", c);
        Worker worker2 = new Worker("worker-thread-2", c);
        Worker worker3 = new Worker("worker-thread-3", c);

        // 启动所有线程
        waitThread1.start();
        waitThread2.start();
        Thread.sleep(1000);
        worker1.start();
        worker2.start();
        worker3.start();
    }
}
