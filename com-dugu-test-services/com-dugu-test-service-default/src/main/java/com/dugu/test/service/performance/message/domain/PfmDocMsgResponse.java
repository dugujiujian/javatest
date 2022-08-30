package com.dugu.test.service.performance.message.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author cihun
 * @date 2022-08-05 3:45 下午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PfmDocMsgResponse implements Serializable {

    private static final long serialVersionUID = 1704119572931268574L;
    /**
     * 当前时间
     */
    private Date now;
    /**
     * 文档信息
     */
    private PfmDocDTO doc;
    /**
     * 计划
     */
    private PfmPlanDTO plan;
    /**
     * 模版
     */
    private PfmTemplateInfoDTO template;
    /**
     * 当前节点配置
     */
    private ProcessConfigDTO processConfig;
    /**
     * 日志保存参数
     */
    private List<PfmRemindLogSaveRequest> batchRequest;


}