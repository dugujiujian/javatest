package com.dugu.test.service.design.cor.perf;

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
     * @return true 处理成功
     */
    void doHandler(DocProcessRequest request,
                      DocProcessResponse response,
                      PerformanceHandlerChain chain);

    /**
     * 错误信息处理
     *
     * @param request
     * @param response
     */
    void onException(DocProcessRequest request, DocProcessResponse response);

    /**
     * 排序
     *
     * @return 序号
     */
    int getOrder();
}
