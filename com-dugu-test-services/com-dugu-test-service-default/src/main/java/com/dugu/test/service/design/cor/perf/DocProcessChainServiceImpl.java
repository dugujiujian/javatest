package com.dugu.test.service.design.cor.perf;

import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cihun
 * @date 2022-07-24 9:28 上午
 */
@Component
public class DocProcessChainServiceImpl implements DocProcessChainService, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    /**
     * 处理对象列表
     */
    private List<PerformanceHandler> handlerList;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean doHandler(DocProcessRequest request, DocProcessResponse response) {
        PerformanceHandlerChain performanceHandlerChain = new PerformanceHandlerChain();
        performanceHandlerChain.handlers = handlerList;
        performanceHandlerChain.current = 0;
        if (CollectionUtils.isNotEmpty(handlerList)) {
            PerformanceHandler performanceHandler = handlerList.get(0);
            try {
                performanceHandler.doHandler(request, response, performanceHandlerChain);
                return true;
            } catch (Exception ex) {
                handlerList.forEach(e -> e.onException(request, response));
            }
        }
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, PerformanceHandler> map = applicationContext.getBeansOfType(PerformanceHandler.class);
        handlerList = map.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(PerformanceHandler::getOrder))
                .collect(Collectors.toList());
    }


}
