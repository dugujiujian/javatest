package com.dugu.test.service.oop.dip;

import com.dugu.test.service.oop.dip.biz.DingTalk;
import com.dugu.test.service.oop.dip.biz.Email;
import com.dugu.test.service.oop.dip.biz.SendMsgDip;
import org.junit.Test;

/**
 * @author cihun
 * @date 2022-08-30 5:30 下午
 */
public class SendMsgDipTest {

    @Test
    public  void testSendMsg(){
        SendMsgDip sendMsg=new SendMsgDip(new Email());
        sendMsg.sendMsg();
        sendMsg=new SendMsgDip(new DingTalk());
        sendMsg.sendMsg();
    }
}
