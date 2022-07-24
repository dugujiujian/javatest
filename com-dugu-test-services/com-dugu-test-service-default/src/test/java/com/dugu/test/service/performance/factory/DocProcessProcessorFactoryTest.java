package com.dugu.test.service.performance.factory;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.biz.DocObjectEntryProcessor;
import com.dugu.test.service.performance.factory.biz.DocObjectivesApprovalProcessor;
import com.dugu.test.service.performance.msg.DocMessageSendStrategyContext;
import com.dugu.test.service.performance.msg.biz.DocObjectEntryMsgProcessor;
import com.dugu.test.service.performance.msg.biz.DocObjectivesApprovalMsgProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author cihun
 * @date 2022-07-23 4:49 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                DocProcessProcessorFactory.class,
                DocMessageSendStrategyContext.class,
                DocObjectEntryProcessor.class,
                DocObjectivesApprovalProcessor.class,
                DocObjectEntryMsgProcessor.class,
                DocObjectivesApprovalMsgProcessor.class})
public class DocProcessProcessorFactoryTest {


    @Autowired
    private DocProcessProcessorFactory docProcessProcessorFactory;


    @Test
    public void testObjectEntry() {
        DocProcessProcessor processProcessor = docProcessProcessorFactory.getProcessor(ProcessCodeEnum.objectives_approval);
        Assert.assertNotNull(processProcessor);
        DocProcessRequest request = new DocProcessRequest();
        DocProcessResponse response = new DocProcessResponse();
        processProcessor.execute(request, response);
    }

}