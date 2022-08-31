package com.dugu.test.service.performance.factory.biz;

import com.dugu.test.service.design.cor.perf.DocProcessChainService;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.AbsDocProcessProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 11:44 下午
 */
@Component
public class DocEmployeeScoreProcessor extends AbsDocProcessProcessor {

    @Autowired
    private DocProcessChainService docProcessChainService;

    @Override
    public boolean doHandler(DocProcessResponse response) {
        return docProcessChainService.doHandler(new DocProcessRequest(), response);
    }

    @Override
    public boolean after(DocProcessResponse response) {
        return true;
    }

    @Override
    public ProcessCodeEnum getCurProcessCode() {
        return ProcessCodeEnum.employee_score;
    }
}
