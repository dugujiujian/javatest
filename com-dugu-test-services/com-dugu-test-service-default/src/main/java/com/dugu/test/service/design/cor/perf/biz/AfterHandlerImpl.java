package com.dugu.test.service.design.cor.perf.biz;

import com.dugu.test.service.design.cor.perf.PerformanceHandler;
import com.dugu.test.service.design.cor.perf.PerformanceHandlerChain;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import org.springframework.stereotype.Service;

/**
 * @author cihun
 * @date 2022-07-24 9:57 上午
 */
@Service
public class AfterHandlerImpl implements PerformanceHandler {
    @Override
    public void doHandler(DocProcessRequest request, DocProcessResponse response, PerformanceHandlerChain chain) {
        System.out.println("我是第" + getOrder() + "个处理器:我专门处理后续业务");
        chain.doHandler(request, response);
    }

    @Override
    public void onException(DocProcessRequest request, DocProcessResponse response) {

    }

    @Override
    public int getOrder() {
        return 4;
    }
}
