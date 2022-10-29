package com.dugu.test.service.performance.agency;

import com.dugu.test.service.performance.agency.enums.AgencyBizTypeEnum;
import com.dugu.test.service.performance.message.PfmDocMessageHandler;
import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 代办抽象工厂
 *
 * @author cihun
 * @date 2022-07-26 11:40 上午
 */
@Component
public class AgencyFactory implements InitializingBean, ApplicationContextAware {

    public static Map<AgencyBizTypeEnum, AgencyService> docProcessActionMap = Maps.newHashMap();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, AgencyService> map = applicationContext.getBeansOfType(AgencyService.class);
        for (Map.Entry<String, AgencyService> data : map.entrySet()) {
            AgencyBizTypeEnum processAction = data.getValue().getBizType();
            if (processAction != null) {
                docProcessActionMap.put(processAction, data.getValue());
            }
        }

    }

    public AgencyService getHandler(AgencyBizTypeEnum agencyBizType) {

        if (agencyBizType != null) {
            return docProcessActionMap.get(agencyBizType);
        }
        return null;
    }

}
