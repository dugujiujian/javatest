package com.dugu.test.service.java8.list;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 2:28 PM
 */
public class ListArrayDemo {

    public List<String> convertByArr(String[] arr) {
        return Stream.of(arr).collect(Collectors.toList());
    }

    public List<String> convertFromString(String s) {
        return Stream.of(StringUtils.split(s, ",")).collect(Collectors.toList());
    }

    public String[] convertFromString(List<String> list) {
        return list.stream().toArray(String[]::new);
    }


    public static String toString(List<Integer> list) {
        return list.stream().map(String::valueOf).distinct().collect(joining(","));
    }

    public static List<Integer> stringToList(String s) {
        return Stream.of(s)
                .map(item -> item.split(","))
                .flatMap(Arrays::stream)
                .mapToInt(Integer::valueOf)
                .boxed()
                .collect(Collectors.toList());
    }
}
