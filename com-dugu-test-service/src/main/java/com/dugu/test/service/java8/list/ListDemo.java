package com.dugu.test.service.java8.list;

import com.dugu.test.service.java8.list.domain.Apple;

import java.awt.font.NumericShaper;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 10:50 AM
 */
public class ListDemo {


    public static long count(List<Integer> list) {
        return list.stream().count();
    }

    public static List<Integer> distinct(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static List<Integer> sortAsc(List<Integer> list) {
        return list.stream().distinct().sorted().collect(Collectors.toList());
    }

    public static List<Integer> sortDesc(List<Integer> list) {
        return list.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    public static Map<Boolean, List<Integer>> oddEven(List<Integer> list) {
        return list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
    }



}
