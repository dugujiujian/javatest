package com.dugu.test.service.performance.message;

import com.dugu.test.service.performance.message.domain.PfmDocMsgRequest;
import com.dugu.test.service.performance.message.domain.PfmDocMsgResponse;
import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;

/**
 * @author cihun
 * @date 2022-08-05 3:42 下午
 */
public interface PfmDocMessageHandler<S,E> {

    /**
     * 消息发送
     *
     * @param request  请求参数
     * @param response 输出参数
     * @return 是否成功发送
     */
    boolean sendMessage(PfmDocMsgRequest request, PfmDocMsgResponse response);


    /**
     * 当前文档行为
     *
     * @return {@link DocActionEnum}
     */
    E getDocAction();
}
