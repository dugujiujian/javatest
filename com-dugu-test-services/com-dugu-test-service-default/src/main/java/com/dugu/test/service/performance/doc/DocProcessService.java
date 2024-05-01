package com.dugu.test.service.performance.doc;

import com.dugu.test.service.performance.doc.domain.DocProcessRequest;
import com.dugu.test.service.performance.doc.domain.DocProcessResponse;
import com.dugu.test.service.performance.doc.domain.DocProcessResult;

/**
 *
 *
 * @Description TODO
 * @Author by cihun
 * @Date 2022/12/11 11:34
 */
public interface DocProcessService {

    /**
     * 业务处理
     *
     * @param request
     * @param response
     * @return
     */
    DocProcessResult process(DocProcessRequest request, DocProcessResponse response);
}
