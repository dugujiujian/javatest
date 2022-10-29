package com.dugu.test.service.performance.agency;

import com.dugu.test.service.performance.agency.domain.AgencyRequest;
import com.dugu.test.service.performance.agency.domain.AgencyResponse;
import com.dugu.test.service.performance.agency.domain.AgencyResult;
import com.dugu.test.service.performance.agency.enums.AgencyBizTypeEnum;
import com.dugu.test.service.performance.agency.enums.TaskActionTypeEnum;
import com.dugu.test.service.performance.agency.handler.ObjectApproveAgencyHandler;
import com.dugu.test.service.performance.agency.handler.WaitSetAgencyHandler;
import com.dugu.test.service.performance.domain.model.Context;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 19:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {
        AgencyFactory.class,
                WaitSetAgencyHandler.class,
                ObjectApproveAgencyHandler.class})
public class AgencyFactoryTest extends TestCase {


    @Autowired
    private AgencyFactory agencyFactory;

    @Test
    public void testWaitSetAgency() {
        AgencyService agencyService = agencyFactory.getHandler(AgencyBizTypeEnum.WAITING_SET);
        AgencyRequest request = AgencyRequest.builder().context(new Context())
                .appId("appId")
                .planId("planId")
                .receiveUserId("receiveId")
                .taskActionTypeEnum(TaskActionTypeEnum.CREATE)
                .build();
        AgencyResult result = agencyService.execute(request, new AgencyResponse());
        System.out.println(agencyService.getBizType()+"==="+result.getMsg());


        request = AgencyRequest.builder().context(new Context())
                .appId("appId")
                .planId("planId")
                .receiveUserId("receiveId")
                .taskActionTypeEnum(TaskActionTypeEnum.RETURN_BACK)
                .build();
        result = agencyService.execute(request, new AgencyResponse());
        System.out.println(agencyService.getBizType()+"==="+result.getMsg());


        request = AgencyRequest.builder().context(new Context())
                .appId("appId")
                .planId("planId")
                .receiveUserId("receiveId")
                .taskActionTypeEnum(TaskActionTypeEnum.PASS)
                .build();
        result = agencyService.execute(request, new AgencyResponse());
        System.out.println(agencyService.getBizType()+"==="+result.getMsg());

        request = AgencyRequest.builder().context(new Context())
                .appId("appId")
                .planId("planId")
                .receiveUserId("receiveId")
                .taskActionTypeEnum(TaskActionTypeEnum.REVOKE)
                .build();
        result = agencyService.execute(request, new AgencyResponse());
        System.out.println(agencyService.getBizType()+"==="+result.getMsg());

        request = AgencyRequest.builder().context(new Context())
                .appId("appId")
                .planId("planId")
                .receiveUserId("receiveId")
                .taskActionTypeEnum(TaskActionTypeEnum.TURN_OVER)
                .build();
        result = agencyService.execute(request, new AgencyResponse());
        System.out.println(agencyService.getBizType()+"==="+result.getMsg());
    }


    @Test
    public void testObjectApproveAgency() {

    }


}