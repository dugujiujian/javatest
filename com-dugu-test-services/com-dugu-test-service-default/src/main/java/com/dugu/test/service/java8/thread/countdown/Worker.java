package com.dugu.test.service.java8.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author cihun
 * @date 2023-09-10 21:19
 */
public class Worker extends Thread {

    private String name;
    private CountDownLatch c;

    public Worker(String name, CountDownLatch c) {
        this.name = name;
        this.c = c;
    }

    @Override
    public void run() {
        System.out.println(this.name + " is running...");
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + " is end.");
        c.countDown();
    }
}
