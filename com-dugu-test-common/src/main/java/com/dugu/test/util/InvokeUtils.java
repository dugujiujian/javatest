package com.dugu.test.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author cihun
 * @Date 2022-04-02 11:58 下午
 */
public class InvokeUtils {
    public static Field getField(Class<?> clazz, String fieldName) {
        Field[] fields = getAllField(clazz);

        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        throw new NullPointerException("在" + clazz.getName() + "中找不到属性：" + fieldName);
    }


    public static Field[] getAllField(Class<?> clazz) {
        List<Field> allFields = new ArrayList<Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        allFields.addAll(Arrays.asList(declaredFields));

        Class<?> parentClazz = clazz.getSuperclass();
        while (parentClazz != null && parentClazz != Object.class) {
            Field[] parentDeclaredFields = parentClazz.getDeclaredFields();
            allFields.addAll(Arrays.asList(parentDeclaredFields));

            parentClazz = parentClazz.getSuperclass();
        }
        Field[] fields = allFields.toArray(new Field[] {});
        return fields;
    }

    public static Object getValue(Object thisObject, String fieldName) {
        if (fieldName == null || fieldName.equals("") || thisObject == null) {
            return null;
        }
        Object result;

        Method method = getGetMethod(thisObject.getClass(), fieldName);
        if (method != null) {
            boolean accessible = method.isAccessible();
            try {
                if (!accessible) {
                    method.setAccessible(true);
                }
                try {
                    result = method.invoke(thisObject);
                } catch (Exception e) {
                    throw new RuntimeException("获取属性值失败：" + fieldName, e);
                }
            } finally {
                if (!accessible) { method.setAccessible(accessible); }
            }
        } else {
            Field declaredField = getField(thisObject.getClass(), fieldName);
            boolean accessible = declaredField.isAccessible();
            try {
                if (!accessible) {
                    declaredField.setAccessible(true);
                }
                try {
                    result = declaredField.get(thisObject);
                } catch (Exception e) {
                    throw new RuntimeException("获取属性值失败：" + fieldName, e);
                }
            } finally {
                if (!accessible) { declaredField.setAccessible(accessible); }
            }
        }

        return result;
    }

    private static Method getGetMethod(Class<? extends Object> clazz,
                                       String fieldName) {
        return getGetMethod(clazz, fieldName, true);
    }

    private static Method getGetMethod(Class<? extends Object> clazz,
                                       String fieldName, boolean isGet) {
        String getMethodName = new StringBuilder("get").append(fieldName).toString();
        String isMethodName = new StringBuilder("is").append(fieldName).toString();
        String setMethodName = new StringBuilder("set").append(fieldName).toString();

        List<Method> allMethods = new ArrayList<Method>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        allMethods.addAll(Arrays.asList(declaredMethods));

        Class<?> parentClazz = clazz.getSuperclass();
        while (parentClazz != null && parentClazz != Object.class) {
            Method[] parentDeclaredMethods = parentClazz.getDeclaredMethods();
            allMethods.addAll(Arrays.asList(parentDeclaredMethods));

            parentClazz = parentClazz.getSuperclass();
        }
        Method[] methods = allMethods.toArray(new Method[] {});

        for (Method method : methods) {
            if (isGet) {
                if (method.getName().equalsIgnoreCase(getMethodName)) {
                    return method;
                } else if (method.getName().equalsIgnoreCase(isMethodName)) {
                    return method;
                }
            } else {
                if (method.getName().equalsIgnoreCase(setMethodName)) {
                    return method;
                }
            }
        }

        return null;
    }
}
