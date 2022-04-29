package com.dugu.test.service.java8.thread.exec;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author cihun
 * @Date 2022-04-13 8:35 下午
 */
public class ThreadPoolUtilTest {
    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.getInstance().getExecutor();

    @Test
    public void testThreadPoolExecutor() {
        List<Callable<Boolean>> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            String v = "Test" + i;
            Callable<Boolean> callable = () -> {
                System.out.println(v);
                System.out.println(Thread.currentThread().getName());
                return true;
            };
            list.add(callable);
        }

        try {
            List<Future<Boolean>> futures = threadPoolExecutor.invokeAll(list);
            for (Future<Boolean> t : futures) {
                System.out.println(t.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}