package com.dugu.test.service.performance.message.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * @author cihun
 * @date 2022-08-07 11:01 下午
 */

@Setter
@Getter
@ToString
public class PfmDocMsgBatchSendRequest extends PfmDocMsgRequest {

    /**
     * 批量文档ID,和docList二选其一(注意控制数量,最佳50条)
     */
    private Collection<String> docIdList;
    /**
     * 传入实体对象,和docIdList二选其一(注意控制数量,最佳50条)
     */
    private Collection<PfmDocDTO> docList;
    /**
     * 是否聚合用户
     */
    private boolean aggregateReceiveUser;

}
