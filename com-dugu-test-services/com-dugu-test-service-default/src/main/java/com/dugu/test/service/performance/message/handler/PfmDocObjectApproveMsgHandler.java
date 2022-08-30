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
 * @date 2022-08-05 5:48 下午
 */
@Component
public class PfmDocObjectApproveMsgHandler extends AbsPfmDocMessageHandler<ProcessCodeEnum, DocActionEnum> {

    @Override
    protected boolean doBatchHandle(PfmDocMsgBatchSendRequest request, PfmDocMsgResponse response) {
        // 获取用户列表
        // 获取消息模版
        // 组装消息日志参数
        // 发送消息
        System.out.println(onProcessCode()+"->batch");
        return true;
    }

    @Override
    protected boolean doHandle(PfmDocMsgSingleSendRequest request, PfmDocMsgResponse response) {
        System.out.println(onProcessCode()+"->single");
        return true;
    }

    @Override
    protected void msgLog(PfmDocMsgResponse response) {
        System.out.println(onProcessCode()+"->msgLog");
    }

    @Override
    protected ProcessCodeEnum onProcessCode() {
        return ProcessCodeEnum.objectives_approval;
    }

    @Override
    public DocActionEnum getDocAction() {
        return DocActionEnum.objectives_approval;
    }
}
