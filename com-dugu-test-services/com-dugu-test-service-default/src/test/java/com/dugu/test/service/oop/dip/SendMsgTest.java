package com.dugu.test.service.oop.dip;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author cihun
 * @date 2022-08-31 6:25 下午
 */
public class SendMsgTest extends TestCase {

    @Test
    public  void testSendMsg(){
        SendMsg sendMsg=new SendMsg();
        sendMsg.sendDD();
        sendMsg.sendEmail();

    }
}