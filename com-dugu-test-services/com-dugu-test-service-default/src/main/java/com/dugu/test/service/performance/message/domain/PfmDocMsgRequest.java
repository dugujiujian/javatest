package com.dugu.test.service.performance.message.domain;

import com.dugu.test.service.performance.domain.model.Context;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2022-08-05 3:43 下午
 */
@Setter
@Getter
@ToString
public class PfmDocMsgRequest {
    /**
     * 系统上下文
     */
    private Context context;
    /**
     * appId
     */
    private String appId;
    /**
     * 计划Id,必须填写
     */
    private String planId;
    /**
     * 是否使用外部初始化对象,是指 plan/template/config 等对象由外部传入
     */
    private Boolean useOuterInitObject;
}
