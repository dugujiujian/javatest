package com.dugu.test.service.design.cor.perf;

import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;

/**
 * @author cihun
 * @date 2022-07-24 9:27 上午
 */
public interface DocProcessChainService {

    /**
     * 外部调用接口
     *
     * @param request
     * @param response
     * @return
     */
    boolean doHandler(DocProcessRequest request, DocProcessResponse response);
}
