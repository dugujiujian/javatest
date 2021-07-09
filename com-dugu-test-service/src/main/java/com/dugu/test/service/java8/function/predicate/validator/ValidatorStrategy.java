package com.dugu.test.service.java8.function.predicate.validator;

import java.util.function.Predicate;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/9/3 11:36 PM
 */
public interface ValidatorStrategy {

    /**
     *
     * @param p
     * @return
     */
    boolean execute(String p);
}
