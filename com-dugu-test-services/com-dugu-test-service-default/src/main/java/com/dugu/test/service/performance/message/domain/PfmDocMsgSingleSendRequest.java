package com.dugu.test.service.performance.message.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 单文档发送
 *
 * @author cihun
 * @date 2022-07-28 3:23 下午
 */
@Setter
@Getter
@ToString
public class PfmDocMsgSingleSendRequest extends PfmDocMsgRequest {
    private static final long serialVersionUID = -7349987532134730591L;
    /**
     * 文档Id
     */
    private String docId;
    /**
     * 文档
     */
    private PfmDocDTO doc;
    /**
     * 消息接收者指定
     */
    private List<UserSimpleDTO> receiveUserList;
}
