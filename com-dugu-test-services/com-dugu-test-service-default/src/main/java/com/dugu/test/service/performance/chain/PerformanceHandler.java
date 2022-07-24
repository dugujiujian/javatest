package com.dugu.test.service.performance.chain;

import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;

/**
 * @author cihun
 * @date 2022-07-23 2:51 下午
 */
public interface PerformanceHandler {

    /**
     * 责任链模式执行
     *
     * @param request
     * @param response
     * @param chain
     */
    void doHandler(DocProcessRequest request,
                   DocProcessResponse response,
                   PerformanceHandlerChain chain);

    /**
     * 排序
     *
     * @return 序号
     */
    int getOrder();
}
