package com.dugu.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cihun
 * @date 2020/04/20
 */
public class AnyTest {



    @Test
    public void test(){

        Map<String,Object> map=new HashMap<>();

        AnyTest d=(AnyTest)map.get("A");

        System.out.println(StringUtils.joinWith("_", "test", "dd"));

    }


}

