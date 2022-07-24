package com.dugu.test.service.performance.msg;

import com.dugu.test.service.performance.domain.response.DocProcessResponse;

/**
 * @author cihun
 * @date 2022-07-23 11:49 下午
 */
public interface DocMessageStrategy {

    /**
     * 当前节点消息发送
     *
     * @param response 输出参数
     */
    void sendMessage(DocProcessResponse response);

    /**
     * 下个节点消息发送
     *
     * @param response 输出参数
     */
    void sendNextNodeMessage(DocProcessResponse response);
}
