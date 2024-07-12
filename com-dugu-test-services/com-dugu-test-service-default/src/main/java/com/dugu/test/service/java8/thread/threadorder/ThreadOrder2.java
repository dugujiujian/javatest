package com.dugu.test.service.java8.thread.threadorder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cihun
 * @date 2023-09-10 21:05
 */
public class ThreadOrder2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Thread1());
        executor.submit(new Thread2());
        executor.submit(new Thread3());
        executor.shutdown();
    }

    public static class Thread1 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread1");
        }
    }

    public static class Thread2 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread2");
        }
    }

    public static class Thread3 implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread3");
        }
    }

}
