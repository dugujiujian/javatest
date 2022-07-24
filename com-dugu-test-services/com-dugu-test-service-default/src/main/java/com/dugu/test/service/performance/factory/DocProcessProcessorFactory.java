package com.dugu.test.service.performance.factory;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cihun
 * @date 2022-07-23 3:31 下午
 */
@Component
public class DocProcessProcessorFactory implements InitializingBean, ApplicationContextAware {

    public static Map<ProcessCodeEnum, DocProcessProcessor> connectionMap = Maps.newHashMap();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 用接口实现
        Map<String, DocProcessProcessor> map = applicationContext.getBeansOfType(DocProcessProcessor.class);
        for (Map.Entry<String, DocProcessProcessor> data : map.entrySet()) {
            ProcessCodeEnum processCodeEnum = data.getValue().getCurProcessCode();
            if (processCodeEnum != null) {
                connectionMap.put(processCodeEnum, data.getValue());
            }
        }
        // 注解类实现
//        Map<String, Object> mapAnnotation = applicationContext.getBeansWithAnnotation(ProcessCodeType.class);
//        for (Object bean : mapAnnotation.values()) {
//            if (!(bean instanceof DocProcessProcessor)) {
//                continue;
//            }
//            ProcessCodeType processCodeType = bean.getClass().getAnnotation(ProcessCodeType.class);
//            connectionMap.put(processCodeType.type(), (DocProcessProcessor) bean);
//        }
    }


    public DocProcessProcessor getProcessor(ProcessCodeEnum processCode) {
        if (processCode != null) {
            return connectionMap.get(processCode);
        }
        return null;
    }
}
