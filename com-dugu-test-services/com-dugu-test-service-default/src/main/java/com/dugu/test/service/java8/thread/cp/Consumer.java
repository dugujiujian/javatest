package com.dugu.test.service.java8.thread.cp;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author cihun
 * @date 2022-03-21 11:31 下午
 */
public class Consumer extends Thread {

    private LinkedBlockingDeque<String> queue;

    Consumer(LinkedBlockingDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            int nextInt = new Random().nextInt(10000);
            try {
                String take = queue.take();
                System.out.println(Thread.currentThread()+":消费者消费："+take);
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
