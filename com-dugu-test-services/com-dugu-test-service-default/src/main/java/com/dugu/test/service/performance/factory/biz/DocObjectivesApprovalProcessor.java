package com.dugu.test.service.performance.factory.biz;

import com.dugu.test.service.performance.aop.ProcessCodeType;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.AbsDocProcessProcessor;
import com.dugu.test.service.performance.msg.DocMessageSendStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 11:00 下午
 */
@Component
public class DocObjectivesApprovalProcessor extends AbsDocProcessProcessor {

    @Autowired
    private DocMessageSendStrategyContext docMessageSendStrategyContext;

    @Override
    public boolean doHandler(DocProcessResponse response) {
        System.out.println(getCurProcessCode() + ":handle");
        return true;
    }

    @Override
    public boolean after(DocProcessResponse response) {
        // 清理待办
        // 发送消息
        System.out.println(getCurProcessCode() + ":after");
        docMessageSendStrategyContext
                .getMessageStrategy(ProcessCodeEnum.objectives_approval)
                .sendMessage(response);
        docMessageSendStrategyContext
                .getMessageStrategy(ProcessCodeEnum.objectives_approval)
                .sendNextNodeMessage(response);
        return true;
    }

    @Override
    public ProcessCodeEnum getCurProcessCode() {
        return ProcessCodeEnum.objectives_approval;
    }
}
