package com.dugu.test.service.lst.data;

import com.dugu.test.service.design.cor.perf.DocProcessChainService;
import com.dugu.test.service.design.cor.perf.DocProcessChainServiceImpl;
import com.dugu.test.service.design.cor.perf.biz.AfterHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.BizHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.InitHandlerImpl;
import com.dugu.test.service.design.cor.perf.biz.MessageHandlerImpl;
import com.dugu.test.service.java8.function.response.File;
import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;
import com.dugu.test.service.lst.data.handler.AdminTaskStatHandler;
import com.dugu.test.service.lst.data.handler.FilterHandler;
import com.dugu.test.service.lst.data.handler.MarkProgressHandler;
import com.dugu.test.service.lst.data.handler.ProgressHandler;
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
 * @date 2022-08-31 2:41 下午
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class,
        classes = {
                DataServiceImpl.class,
                AdminTaskStatHandler.class,
                FilterHandler.class,
                MarkProgressHandler.class,
                ProgressHandler.class})
public class DataServiceImplTest extends TestCase {

    @Autowired
    private DataService dataService;

    @Test
    public void testChain() {
        HandlerResponse response=new HandlerResponse();
        RowModel rowModel=new RowModel();
        dataService.doHandle(rowModel,response);
    }


}