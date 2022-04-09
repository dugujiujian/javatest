package com.dugu.test.service.java8.thread.cp;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author cihun
 * @Date 2022-03-25 10:09 上午
 */
public class Product extends Thread {
    private LinkedBlockingDeque<String> queue;

    Product(LinkedBlockingDeque<String> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int nextInt = new Random().nextInt(5000);
            try {
                queue.put("物品" + i);
                System.out.println("生产者生产:" + i);
                Thread.sleep(nextInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
