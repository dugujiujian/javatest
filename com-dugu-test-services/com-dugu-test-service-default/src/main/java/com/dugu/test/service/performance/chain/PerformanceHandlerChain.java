package com.dugu.test.service.performance.chain;

import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import lombok.Setter;

import java.util.List;

/**
 * 责任脸
 *
 * @author cihun
 * @date 2022-07-23 2:50 下午
 */
public class PerformanceHandlerChain {

    /**
     * 处理器集合
     */
    @Setter
    private List<PerformanceHandler> handlers;
    /**
     * 处理器索引下标
     */
    private int current = 0;

    public void doHandler(DocProcessRequest request, DocProcessResponse response) {
        current++;
        if (current < handlers.size()) {
            handlers.get(current).doHandler(request, response, this);
        }
    }
}
