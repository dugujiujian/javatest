package com.dugu.test.service.java8.function.consumer;

import java.util.function.BiConsumer;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/23 12:40 PM
 */
public class BiConsumerDemo {


    static <T> void add(T a, T b, BiConsumer<T, T> c) {
        c.accept(a, b);
    }
}
