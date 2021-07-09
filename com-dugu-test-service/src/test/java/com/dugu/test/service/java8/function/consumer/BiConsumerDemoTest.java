package com.dugu.test.service.java8.function.consumer;

import org.junit.Test;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/23 12:40 PM
 */
public class BiConsumerDemoTest {

    @Test
    public void add() {
        BiConsumerDemo.add(1, 2, (x, y) -> System.out.println(x + y));
        BiConsumerDemo.add("Hello!", "Cattle", (x, y) -> System.out.println(x + y));
    }
}