package com.dugu.test.service.java8.function.predicate;

import com.dugu.test.service.java8.function.predicate.domain.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Description TODO
 * @Author by zlp
 * @Date 2020/6/21 12:22 PM
 */
public class PredicateDemo {


    public static boolean isRed(Apple apple) {
        return "red".equals(apple.getColor());
    }


    public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> applePredicate) {
        List<Apple> result = new ArrayList<>();
        apples.forEach(item -> {
            if (applePredicate.test(item)) {
                result.add(item);
            }
        });
        return result;
    }
}
