package com.dugu.test.service.java8.function.predicate;

import com.dugu.test.service.java8.function.predicate.validator.Validation;
import org.junit.Test;

import javax.xml.validation.Validator;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/9/5 9:05 AM
 */
public class ValidationTest {


    @Test
    public void test(){
        Validation v=new Validation((String s)->s.matches("\\d+"));

        System.out.println("number="+v.excute("11111"));
    }
}
