package com.dugu.test.service.performance.factory.biz;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import com.dugu.test.service.performance.factory.AbsDocProcessProcessor;
import com.dugu.test.service.performance.msg.DocMessageSendStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 10:58 上午
 */
@Component
public class DocObjectEntryProcessor extends AbsDocProcessProcessor {

    @Autowired
    private DocMessageSendStrategyContext docMessageSendStrategyContext;

    @Override
    public boolean doHandler(DocProcessResponse response) {
        System.out.println(getCurProcessCode() + ":handle");
        // 其他业务逻辑处理
        // 更新文档状态
        return true;
    }

    @Override
    public boolean after(DocProcessResponse response) {
        // 清理待办
        // 发送消息
        System.out.println(getCurProcessCode() + ":after");
        docMessageSendStrategyContext
                .getMessageStrategy(ProcessCodeEnum.objectives_entry)
                .sendMessage(response);
        docMessageSendStrategyContext
                .getMessageStrategy(ProcessCodeEnum.objectives_entry)
                .sendNextNodeMessage(response);
        return true;
    }

    @Override
    public ProcessCodeEnum getCurProcessCode() {
        return ProcessCodeEnum.objectives_entry;
    }
}
