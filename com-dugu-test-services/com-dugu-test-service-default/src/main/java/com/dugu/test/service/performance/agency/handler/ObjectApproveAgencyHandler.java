package com.dugu.test.service.performance.agency.handler;

import com.dugu.test.service.performance.agency.AbsAgencyHandler;
import com.dugu.test.service.performance.agency.AgencyService;
import com.dugu.test.service.performance.agency.domain.AgencyRequest;
import com.dugu.test.service.performance.agency.domain.AgencyResponse;
import com.dugu.test.service.performance.agency.domain.AgencyResult;
import com.dugu.test.service.performance.agency.enums.AgencyBizTypeEnum;
import org.springframework.stereotype.Service;

/**
 * 目标审批
 *
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 19:42
 */
@Service
public class ObjectApproveAgencyHandler extends AbsAgencyHandler implements AgencyService {
    @Override
    protected AgencyResult create(AgencyRequest request, AgencyResponse response) {
        return AgencyResult.builder().success(true).msg("create").build();
    }

    @Override
    protected AgencyResult pass(AgencyRequest request, AgencyResponse response) {
        return AgencyResult.builder().success(true).msg("pass").build();
    }

    @Override
    protected AgencyResult returnBack(AgencyRequest request, AgencyResponse response) {
        return AgencyResult.builder().success(true).msg("returnBack").build();
    }

    @Override
    protected AgencyResult revoke(AgencyRequest request, AgencyResponse response) {
        return AgencyResult.builder().success(true).msg("revoke").build();
    }

    @Override
    protected AgencyResult turnOver(AgencyRequest request, AgencyResponse response) {
        return AgencyResult.builder().success(true).msg("turnOver").build();
    }

    @Override
    public AgencyBizTypeEnum getBizType() {
        return AgencyBizTypeEnum.OBJECTIVE_APPROVAL;
    }
}
