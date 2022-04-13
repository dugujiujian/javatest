package com.dugu.test.service.java8.demo;

import com.dugu.test.service.java8.file.io.FileDemo;
import com.dugu.test.service.java8.file.io.FileDemoTest;
import com.dugu.test.service.java8.function.predicate.PredicateDemo;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/7/15 10:30 PM
 */
public class DemoTest {

    private static Map<String, Integer> map = new HashMap<>();

    public static void print(Object t) {
        String key = t.getClass().getName();
        int hashCode=objFieldList(t).hashCode();
        if (map.get(key) != null && hashCode==map.get(key)) {
            System.out.println("exist");
        } else {
            System.out.println(t.getClass().getName());
        }
        map.put(key, hashCode);
    }

    public static List<String> objFieldList(Object obj) {
        if (obj == null) {
            return null;
        }
        //获取这个类的所有属性
        List<String> list = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean flag = false;
        //循环遍历所有的fields
        for (int i = 0; i < fields.length; i++) {
            list.add(fields[i].getName());
        }
        return list;
    }

    @Test
    public void testF() {

        DemoTest test = new DemoTest();
        test.print(new TestObject());
        test.print(new TestObject());
    }
}
