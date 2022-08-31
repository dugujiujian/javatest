package com.dugu.test.service.design.cor.demo1;

import com.dugu.test.service.design.cor.perf.DocProcessChainService;
import com.dugu.test.service.design.cor.perf.DocProcessChainServiceImpl;
import com.dugu.test.service.design.cor.perf.biz.AfterHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.BizHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.InitHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.MessageHandlerImpl;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author cihun
 * @date 2022-08-30 7:55 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                DocProcessChainServiceImpl.class,
                InitHandlerImpl.class,
                BizHandlerImpl.class,
                AfterHandlerImpl.class,
                MessageHandlerImpl.class})
public class DocProcessChainServiceImplTest extends TestCase {

    @Autowired
    private DocProcessChainService docProcessChainService;

    @Test
    public void testChain() {
        DocProcessRequest request=new DocProcessRequest();
        DocProcessResponse response=new DocProcessResponse();
        docProcessChainService.doHandler(request,response);
    }


    @Test
    public void testThreadChain() {
        DocProcessRequest request=new DocProcessRequest();
        DocProcessResponse response=new DocProcessResponse();
        docProcessChainService.doHandler(request,response);
    }
}