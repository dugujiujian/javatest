package com.dugu.test.service.java8.thread.cp;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @Author cihun
 * @Date 2022-03-25 10:13 上午
 */
public class ProductConsumerTest {


    public static void main(String[] args) {
        LinkedBlockingDeque<String> strings = new LinkedBlockingDeque<>(4);
        new Product(strings).start();
        new Consumer(strings).start();
        new Consumer(strings).start();
        new Consumer(strings).start();
    }
}
