package com.dugu.test.service.java8.function.predicate;

import com.dugu.test.service.java8.function.predicate.domain.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/21 12:32 PM
 */
public class PredicateDemoTest {

    private List<Apple> apples = new ArrayList<>();

    @Before
    public void setUp() {
        Apple apple = new Apple();
        apple.setColor("green");
        apple.setName("绿苹果");
        apple.setPrice(19.9);
        apple.setProductFrom(1);
        apple.setWeight(20.5);
        apples.add(apple);

        apple = new Apple();
        apple.setColor("green");
        apple.setName("绿苹果");
        apple.setPrice(29.9);
        apple.setProductFrom(2);
        apple.setWeight(20.5);
        apples.add(apple);

        apple = new Apple();
        apple.setColor("red");
        apple.setName("红苹果");
        apple.setPrice(9.9);
        apple.setProductFrom(1);
        apple.setWeight(201.5);
        apples.add(apple);
    }

    @Test
    public void filterApples() {
        PredicateDemo.filterApples(apples, (Apple a) -> a.getColor().equals("green")).stream().forEach(System.out::println);
    }

    @Test
    public void filterApplesIsRed() {
        PredicateDemo.filterApples(apples, PredicateDemo::isRed).stream().forEach(System.out::println);
    }
}