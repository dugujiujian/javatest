package com.dugu.test.service.oop.dip.biz;

import com.dugu.test.service.oop.dip.ISendMsg;

/**
 * @author cihun
 * @date 2022-08-30 5:27 下午
 */
public class Email implements ISendMsg {
    @Override
    public void sendMsg() {
        System.out.println("email");
    }
}
