package com.dugu.test.service.java8.function.predicate.validator;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/9/5 9:03 AM
 */
public class Validation {

    private ValidatorStrategy validatorStrategy;


    public Validation(ValidatorStrategy strategy) {
        this.validatorStrategy = strategy;
    }

    public boolean excute(String s) {
       return validatorStrategy.execute(s);
    }


}
