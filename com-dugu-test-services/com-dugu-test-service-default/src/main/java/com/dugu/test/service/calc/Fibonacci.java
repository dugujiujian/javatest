package com.dugu.test.service.calc;

import java.util.HashMap;

/**
 * @Author cihun
 * @Date 2022-04-05 4:33 下午
 */
public class Fibonacci {

    private static HashMap<Integer, Long> hashMap = new HashMap<Integer, Long>();
    private static long value;

    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i <= n; i++) {
            System.out.println(Fibonacci.effectFibonacci(i));
        }
    }




    public static long effectFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (hashMap.containsKey(n)) {
            return hashMap.get(n);
        } else {
            value = effectFibonacci(n - 1) + effectFibonacci(n - 2);
            hashMap.put(n, value);
        }
        return value;
    }
}
