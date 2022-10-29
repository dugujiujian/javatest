package com.dugu.test.service.performance.agency;

import com.dugu.test.service.performance.agency.domain.AgencyRequest;
import com.dugu.test.service.performance.agency.domain.AgencyResponse;
import com.dugu.test.service.performance.agency.domain.AgencyResult;
import com.dugu.test.service.performance.agency.enums.AgencyBizTypeEnum;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 18:46
 */
public interface AgencyService {

    /**
     * 代办任务执行
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    AgencyResult execute(AgencyRequest request, AgencyResponse response);

    /**
     * 获取业务类型
     *
     * @return {@link AgencyBizTypeEnum}
     */
    AgencyBizTypeEnum getBizType();
}
