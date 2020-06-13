package com.dugu.test.service.java8.list;

import com.dugu.test.service.java8.list.domain.Apple;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 11:17 AM
 */
public class ListObjectDemoForAppleTest {

    private List<Apple> appleList = new ArrayList<>();//存放apple对象集合

    @Before
    public void setUp() {

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

    }

    @Test
    public void groupBy() {
        ListObjectDemoForApple.groupBy(appleList).entrySet().stream().forEach(item -> {
            System.out.println("key=" + item.getKey() + ",value=" + item.getValue());
        });
    }

    @Test
    public void groupByDouble() {
        ListObjectDemoForApple.groupByDouble(appleList).entrySet().stream().forEach(item -> {
            System.out.println("key=" + item.getKey() + ",value=" + item.getValue());
        });
    }

    @Test
    public void groupById() {
        ListObjectDemoForApple.groupById(appleList).entrySet().stream().forEach(item -> {
            System.out.println("key=" + item.getKey() + ",value=" + item.getValue());
        });
    }

    @Test
    public void groupByMultiProp() {
        ListObjectDemoForApple.groupByMultiProp(appleList).stream().forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void sum() {
        System.out.println("sum=" + ListObjectDemoForApple.sum(appleList).doubleValue());
    }

    @Test
    public void sum2() {
        System.out.println("sum=" + ListObjectDemoForApple.sum2(appleList));
    }

    @Test
    public void sortAscByNum() {
        ListObjectDemoForApple.sortAsc(appleList).forEach(System.out::println);
    }

    @Test
    public void sortDescByNum() {
        ListObjectDemoForApple.sortDesc(appleList).forEach(System.out::println);
    }
}