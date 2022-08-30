package com.dugu.test.service.performance.message;

import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 消息抽象工厂
 *
 * @author cihun
 * @date 2022-07-26 11:40 上午
 */
@Component
public class PfmDocMessageFactory implements InitializingBean, ApplicationContextAware {

    public static Map<DocActionEnum, PfmDocMessageHandler> docProcessActionMap = Maps.newHashMap();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, PfmDocMessageHandler> map = applicationContext.getBeansOfType(PfmDocMessageHandler.class);
        for (Map.Entry<String, PfmDocMessageHandler> data : map.entrySet()) {
            DocActionEnum processAction = (DocActionEnum)data.getValue().getDocAction();
            if (processAction != null) {
                docProcessActionMap.put(processAction, data.getValue());
            }
        }

    }

    public PfmDocMessageHandler getHandler(DocActionEnum processCode) {

        if (processCode != null) {
            return docProcessActionMap.get(processCode);
        }
        return null;
    }

}
