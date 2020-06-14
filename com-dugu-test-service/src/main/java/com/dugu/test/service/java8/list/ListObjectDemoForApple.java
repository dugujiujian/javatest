package com.dugu.test.service.java8.list;

import com.dugu.test.service.java8.list.domain.Apple;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

/**
 * base on apple
 *
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 11:11 AM
 */
public class ListObjectDemoForApple {

    /**
     * list 转 map
     *
     * @param appleList
     * @return
     */
    public static Map<Integer, List<Apple>> groupBy(List<Apple> appleList) {
        return appleList.stream().collect(Collectors.groupingBy(Apple::getId));
    }

    /**
     * 二重map
     *
     * @param appleList
     * @return
     */
    public static Map<Integer, Map<String, List<Apple>>> groupByDouble(List<Apple> appleList) {
        return appleList.stream().collect(Collectors.groupingBy(Apple::getId, Collectors.groupingBy(Apple::getName)));
    }

    /**
     * list 转 map
     *
     * @param appleList
     * @return
     */
    public static Map<Integer, Apple> groupById(List<Apple> appleList) {
        return appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a, (k1, k2) -> k1));
    }


    /**
     * 多字段去重
     *
     * @param appleList
     * @return
     */
    public static List<Apple> groupByMultiProp(List<Apple> appleList) {
        return appleList.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getId() + ";" + o.getName()))), ArrayList::new));
    }


    public static BigDecimal sum(List<Apple> appleList) {
        return appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static Double sum2(List<Apple> appleList) {
        return appleList.stream().mapToDouble(item -> item.getMoney().doubleValue()).sum();
    }

    public static List<Apple> sortAsc(List<Apple> appleList) {
        return appleList.stream().sorted(Comparator.comparing(Apple::getNum)).collect(Collectors.toList());
    }

    public static List<Apple> sortDesc(List<Apple> appleList) {
        return appleList.stream().sorted(Comparator.comparing(Apple::getNum, Comparator.reverseOrder())).collect(Collectors.toList());
    }

}
