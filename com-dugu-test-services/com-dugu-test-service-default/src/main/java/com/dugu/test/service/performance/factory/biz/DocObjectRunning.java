package com.dugu.test.service.performance.factory.biz;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.AbsDocProcessProcessor;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 11:44 下午
 */
@Component
public class DocObjectRunning extends AbsDocProcessProcessor {
    @Override
    public boolean doHandler(DocProcessResponse response) {
        return false;
    }

    @Override
    public boolean after(DocProcessResponse response) {
        return false;
    }

    @Override
    public ProcessCodeEnum getCurProcessCode() {
        return ProcessCodeEnum.objectives_running;
    }
}
