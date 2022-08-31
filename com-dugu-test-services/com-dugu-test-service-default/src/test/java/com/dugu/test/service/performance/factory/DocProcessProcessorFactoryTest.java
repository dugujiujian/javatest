package com.dugu.test.service.performance.factory;

import com.dugu.test.service.design.cor.perf.DocProcessChainServiceImpl;
import com.dugu.test.service.design.cor.perf.biz.AfterHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.BizHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.InitHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.MessageHandlerImpl;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.biz.DocEmployeeScoreProcessor;
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
                DocProcessChainServiceImpl.class,
                DocObjectEntryProcessor.class,
                DocObjectivesApprovalProcessor.class,
                DocEmployeeScoreProcessor.class,
                DocObjectEntryMsgProcessor.class,
                DocObjectivesApprovalMsgProcessor.class,
                InitHandlerImpl.class,
                BizHandlerImpl.class,
                AfterHandlerImpl.class,
                MessageHandlerImpl.class})
public class DocProcessProcessorFactoryTest {


    @Autowired
    private DocProcessProcessorFactory docProcessProcessorFactory;


    @Test
    public void testObjectEntry() {
        DocProcessProcessor processProcessor = docProcessProcessorFactory.getProcessor(ProcessCodeEnum.objectives_entry);
        //Assert.assertNotNull(processProcessor);
        DocProcessRequest request = new DocProcessRequest();
        request.setDocId("1");
        request.setNextNode(ProcessCodeEnum.employee_score);
        DocProcessResponse response = new DocProcessResponse();
        processProcessor.execute(request, response);
    }

    @Test
    public void testEmployeeScore() {
        DocProcessProcessor processProcessor = docProcessProcessorFactory.getProcessor(ProcessCodeEnum.employee_score);
        Assert.assertNotNull(processProcessor);
        DocProcessRequest request = new DocProcessRequest();
        DocProcessResponse response = new DocProcessResponse();
        processProcessor.execute(request, response);
    }

}