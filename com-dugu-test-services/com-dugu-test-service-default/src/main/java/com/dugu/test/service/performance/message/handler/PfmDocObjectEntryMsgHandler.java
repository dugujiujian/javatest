package com.dugu.test.service.performance.message.handler;

import com.dugu.test.service.performance.message.AbsPfmDocMessageHandler;
import com.dugu.test.service.performance.message.domain.PfmDocMsgBatchSendRequest;
import com.dugu.test.service.performance.message.domain.PfmDocMsgResponse;
import com.dugu.test.service.performance.message.domain.PfmDocMsgSingleSendRequest;
import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;
import com.dugu.test.service.performance.message.domain.enums.ProcessCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-08-28 9:52 下午
 */
@Component
public class PfmDocObjectEntryMsgHandler extends AbsPfmDocMessageHandler<ProcessCodeEnum, DocActionEnum> {

    @Override
    protected boolean doBatchHandle(PfmDocMsgBatchSendRequest request, PfmDocMsgResponse response) {
        // 获取用户列表
        // 获取消息模版
        // 组装消息日志参数
        // 发送消息
        System.out.println(onProcessCode() + "->batch");
        return true;
    }

    @Override
    protected boolean doHandle(PfmDocMsgSingleSendRequest request, PfmDocMsgResponse response) {
        System.out.println(onProcessCode() + "->single");
        return true;
    }

    @Override
    protected ProcessCodeEnum onProcessCode() {
        return ProcessCodeEnum.objectives_entry;
    }

    @Override
    public DocActionEnum getDocAction() {
        return DocActionEnum.objectives_entry;
    }
}
