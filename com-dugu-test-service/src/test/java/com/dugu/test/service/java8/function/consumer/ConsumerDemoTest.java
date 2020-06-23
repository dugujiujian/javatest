package com.dugu.test.service.java8.function.consumer;

import com.dugu.test.service.java8.function.predicate.domain.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/21 3:51 PM
 */
public class ConsumerDemoTest {


    private List<com.dugu.test.service.java8.function.predicate.domain.Apple> apples = new ArrayList<>();

    @Before
    public void setUp() {
        com.dugu.test.service.java8.function.predicate.domain.Apple apple = new com.dugu.test.service.java8.function.predicate.domain.Apple();
        apple.setColor("green");
        apple.setName("绿苹果");
        apple.setPrice(19.9);
        apple.setProductFrom(1);
        apple.setWeight(20.5);
        apples.add(apple);

        apple = new com.dugu.test.service.java8.function.predicate.domain.Apple();
        apple.setColor("green");
        apple.setName("绿苹果");
        apple.setPrice(29.9);
        apple.setProductFrom(2);
        apple.setWeight(20.5);
        apples.add(apple);

        apple = new com.dugu.test.service.java8.function.predicate.domain.Apple();
        apple.setColor("red");
        apple.setName("红苹果");
        apple.setPrice(9.9);
        apple.setProductFrom(1);
        apple.setWeight(201.5);
        apples.add(apple);
    }

    @Test
    public void testSingle() {
        Consumer<Apple> consumer=(Apple a)->System.out.println(a.getName());
        ConsumerDemo.forEach(apples,consumer);
    }

    @Test
    public void testAndThen() {
        Consumer<Apple> consumer=(Apple a)->System.out.println(a.getName());
        Consumer<Apple> consumer2=(Apple a)->System.out.println(a.getName()+"_"+a.getColor());
        ConsumerDemo.forEach(apples,consumer,consumer2);
    }
}