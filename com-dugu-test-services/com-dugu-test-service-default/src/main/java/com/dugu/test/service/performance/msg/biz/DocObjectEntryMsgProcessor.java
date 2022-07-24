package com.dugu.test.service.performance.msg.biz;

import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.msg.DocMessageStrategy;
import org.springframework.stereotype.Service;


/**
 * @author cihun
 * @date 2022-07-23 11:58 下午
 */
@Service("objectives_entry")
public class DocObjectEntryMsgProcessor implements DocMessageStrategy {

    @Override
    public void sendMessage(DocProcessResponse response) {
        System.out.println("objectives_entry:当前节点消息发送");
    }

    @Override
    public void sendNextNodeMessage(DocProcessResponse response) {
        System.out.println(response.getNextProcessCode().name() + ":下个节点节点消息发送");
    }
}
