package com.dugu.test.service.java8.thread.threadorder;


/**
 * 按顺序执行
 *
 * @author cihun
 * @date 2023-09-10 20:58
 */
public class ThreadOrder1 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> System.out.println("t1 is running..."));

        //初始化线程二
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("t2 is running...");
            }
        });

        //初始化线程三
        Thread t3=new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("t3 is running...");
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }

}
