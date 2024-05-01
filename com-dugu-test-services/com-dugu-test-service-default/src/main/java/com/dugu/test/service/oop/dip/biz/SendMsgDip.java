package com.dugu.test.service.oop.dip.biz;

import com.dugu.test.service.oop.dip.ISendMsg;

/**
 * @author cihun
 * @date 2022-08-30 5:22 下午
 */
public class SendMsgDip {

    private ISendMsg iSendMsg;


    public SendMsgDip(ISendMsg iSendMsg) {
        this.iSendMsg = iSendMsg;
    }

    public void sendMsg() {
        iSendMsg.sendMsg();
    }

}
