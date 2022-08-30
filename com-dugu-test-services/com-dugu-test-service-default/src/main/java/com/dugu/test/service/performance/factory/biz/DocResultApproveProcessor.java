package com.dugu.test.service.performance.factory.biz;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.AbsDocProcessProcessor;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 11:47 下午
 */
@Component
public class DocResultApproveProcessor extends AbsDocProcessProcessor {
    @Override
    public boolean doHandler(DocProcessResponse response) {
        return true;
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
