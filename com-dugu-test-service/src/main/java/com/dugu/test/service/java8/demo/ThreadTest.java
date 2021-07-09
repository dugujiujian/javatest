package com.dugu.test.service.java8.demo;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/7/18 11:15 AM
 */
public class ThreadTest extends  Thread{

    @Override
    public void run(){
        System.out.println("from threadTest");
    }



    public static void main(String[] args){
        (new ThreadTest()).start();
    }
}
