package com.dugu.test.service.java8.thread.exec;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

/**
 * @Author cihun
 * @Date 2022-04-13 10:10 下午
 */
public class CustomThreadPool {

    BlockingDeque<Runnable> queue;
    List<Task> thread;

    CustomThreadPool(BlockingDeque<Runnable> queue, int coreSize) {
        this.queue = queue;
        thread = new ArrayList<>();

        IntStream.rangeClosed(0, coreSize).forEach((i) -> {
            Task task = new Task("test" + i);
            task.start();
            thread.add(task);
        });
    }

    public void execute(Runnable runnable) throws InterruptedException {
        queue.put(runnable);
    }


    class Task extends Thread {
        Task(String name) {
            super(name);
        }

        @Override
        public void run() {

            while (true) {
                Runnable task = null;

                try {
                    task = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName());
                }
                task.run();
            }

        }
    }

    public static void main(String[] args) {
        CustomThreadPool customThreadPool = new CustomThreadPool(new LinkedBlockingDeque<>(10), 3);
        IntStream.rangeClosed(0, 5).forEach((i) -> {
            try {
                customThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
            } catch (Exception e) {
            }

        });
    }
}
