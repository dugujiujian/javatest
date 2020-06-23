package com.dugu.test.service.java8.function.consumer;

import java.util.List;
import java.util.function.Consumer;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/20 12:02 PM
 */
public class ConsumerDemo {



    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer, Consumer<T> andThenConsumer) {
        for (T t : list) {
            consumer.andThen(andThenConsumer).accept(t);
        }
    }
}
