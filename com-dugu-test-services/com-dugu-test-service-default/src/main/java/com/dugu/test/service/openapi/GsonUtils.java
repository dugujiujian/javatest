package com.dugu.test.service.openapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Modifier;

/**
 * json相关工具类
 */
public class GsonUtils {
    private static final Gson GSON_INSTANCE = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
            .create();

    public static String toJSONString(Object o) {
        return GSON_INSTANCE.toJson(o);
    }

    public static <T> T parseObject(String jsonString, Class<T> clazz) throws JsonSyntaxException {
        return GSON_INSTANCE.fromJson(jsonString, clazz);
    }
}
