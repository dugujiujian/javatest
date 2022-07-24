package com.dugu.test.service.performance.domain.response;

import com.dugu.test.service.performance.domain.PfmDocDTO;
import com.dugu.test.service.performance.domain.PfmPlanDTO;
import com.dugu.test.service.performance.domain.PfmTemplateDTO;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.model.TemplateNodeConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2022-07-23 10:22 上午
 */
@Getter
@Setter
@ToString
public class DocProcessResponse {

    private PfmDocDTO doc;
    private PfmPlanDTO plan;
    private PfmTemplateDTO template;

    /**
     * 当前节点配置
     */
    private TemplateNodeConfig curConfig;
    /**
     * 下一个节点配置
     */
    private TemplateNodeConfig nextConfig;

    /**
     * 下一个状态位获取
     */
    private ProcessCodeEnum nextProcessCode;
}
