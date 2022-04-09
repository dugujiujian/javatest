package com.dugu.test.service.java8.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 10:52 AM
 */
public class ListDemoTest {


    @Test
    public void count() {
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1,20).forEach(item-> list.add(item));
        Assert.assertEquals(list.size(), ListDemo.count(list));
        list.stream().forEach(System.out::println);
    }

    @Test
    public void distinct() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(6);
        list.add(3);
        List<Integer> result = ListDemo.distinct(list);
        Assert.assertEquals(5, result.size());


        result.stream().forEach(System.out::println);

        ListDemo.sortAsc(list).stream().forEach(System.out::println);

        ListDemo.sortAsc(list).stream().forEach(System.out::println);
    }

    @Test
    public void sortAsc() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(6);
        list.add(3);
        ListDemo.sortAsc(list).stream().forEach(System.out::println);
    }
    @Test
    public void sortDesc() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(6);
        list.add(3);
        ListDemo.sortDesc(list).stream().forEach(System.out::println);
    }

    @Test
    public void oddEven() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        ListDemo.oddEven(list).entrySet().forEach(System.out::println);
    }


}
