package com.dugu.test.service.performance.message;

import com.dugu.test.service.performance.domain.model.Context;
import com.dugu.test.service.performance.message.domain.PfmDocMsgBatchSendRequest;
import com.dugu.test.service.performance.message.domain.PfmDocMsgResponse;
import com.dugu.test.service.performance.message.domain.PfmDocMsgSingleSendRequest;
import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;
import com.dugu.test.service.performance.message.handler.PfmDocObjectApproveMsgHandler;
import com.dugu.test.service.performance.message.handler.PfmDocObjectEntryMsgHandler;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


/**
 * @author cihun
 * @date 2022-08-27 12:56 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                PfmDocMessageFactory.class,
                PfmDocObjectApproveMsgHandler.class,
                PfmDocObjectEntryMsgHandler.class})
public class PfmDocMessageFactoryTest extends TestCase {

    @Autowired
    private PfmDocMessageFactory pfmDocMessageFactory;

    @Test
    public void testObjectApprove() {
        PfmDocMessageHandler handler = pfmDocMessageFactory.getHandler(DocActionEnum.objectives_approval);
        PfmDocMsgSingleSendRequest request = new PfmDocMsgSingleSendRequest();
        request.setDocId("1");
        request.setPlanId("1");
        request.setContext(new Context());
        request.setAppId("1");
        PfmDocMsgResponse response = new PfmDocMsgResponse();
        handler.sendMessage(request, response);


        PfmDocMsgBatchSendRequest batchRequest = new PfmDocMsgBatchSendRequest();
        batchRequest.setPlanId("2");
        batchRequest.setContext(new Context());
        batchRequest.setAppId("1");
        response = new PfmDocMsgResponse();
        handler.sendMessage(batchRequest, response);
    }

    @Test
    public void testObjectEntry() {
        PfmDocMessageHandler handler = pfmDocMessageFactory.getHandler(DocActionEnum.objectives_entry);
        PfmDocMsgSingleSendRequest request = new PfmDocMsgSingleSendRequest();
        request.setDocId("1");
        request.setPlanId("1");
        request.setContext(new Context());
        request.setAppId("1");
        PfmDocMsgResponse response = new PfmDocMsgResponse();
        handler.sendMessage(request, response);


        PfmDocMsgBatchSendRequest batchRequest = new PfmDocMsgBatchSendRequest();
        batchRequest.setPlanId("2");
        batchRequest.setContext(new Context());
        batchRequest.setAppId("1");
        response = new PfmDocMsgResponse();
        handler.sendMessage(batchRequest, response);
    }
}