package com.dugu.test.service.java8.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/13 10:01 PM
 */
public class ListArrayDemoTest {

    @Test
    public void convertByArr() {
    }

    @Test
    public void convertFromString() {
    }

    @Test
    public void convertFromString1() {
    }


    @Test
    public void join() {
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1, 5).forEach(item -> list.add(item));
        System.out.println(ListArrayDemo.toString(list));
    }

    @Test
    public void stringToList() {
        String s = "1,3,5,10,12,3,6,1,89";
        ListArrayDemo.stringToList(s).stream().distinct().sorted().forEach(System.out::println);
    }

}