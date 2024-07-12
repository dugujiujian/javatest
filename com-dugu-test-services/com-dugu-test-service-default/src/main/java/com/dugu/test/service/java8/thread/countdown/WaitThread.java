package com.dugu.test.service.java8.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cihun
 * @date 2023-09-10 21:19
 */
public class WaitThread  extends Thread {

    private String name;
    private CountDownLatch c;

    public WaitThread(String name, CountDownLatch c) {
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        try {
            // 等待
            System.out.println(this.name + " wait...");
            c.await();
            System.out.println(this.name + " continue running...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
