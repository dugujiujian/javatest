package com.dugu.test.service.oop.lsp;

import java.util.Arrays;
import java.util.List;

/**
 * @author cihun
 * @date 2022-08-31 4:14 下午
 */
public class Generics {


    static long sum(List<Number> numbers) {
        long summation = 0;
        for (Number number : numbers) {
            summation += number.longValue();
        }
        return summation;
    }

    public static void main(String[] args) {
        List<Double> myDoubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<Long> myLongs = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<Integer> myInts = Arrays.asList(1,2,3,4,5);
        //System.out.println(Generics.sum(myInts));
    }

}
