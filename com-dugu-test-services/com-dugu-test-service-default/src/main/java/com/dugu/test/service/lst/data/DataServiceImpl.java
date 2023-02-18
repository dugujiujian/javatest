package com.dugu.test.service.lst.data;

import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;
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
 * @date 2022-08-31 2:32 下午
 */
@Component
public class DataServiceImpl implements DataService, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    /**
     * 处理对象列表
     */
    private List<DataHandler> handlerList;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void doHandle(RowModel rowModel, HandlerResponse handlerResponse) {
        DataHandlerChain dataHandlerChain = new DataHandlerChain();
        dataHandlerChain.handlers = handlerList;
        dataHandlerChain.current = 0;
        if (CollectionUtils.isNotEmpty(handlerList)) {
            DataHandler dataHandler = handlerList.get(0);
            try {
                dataHandler.handle(rowModel, handlerResponse, dataHandlerChain);
            } catch (Exception ex) {
                handlerList.forEach(e -> e.onException(rowModel, handlerResponse));
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, DataHandler> map = applicationContext.getBeansOfType(DataHandler.class);
        handlerList = map.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(DataHandler::getOrder))
                .collect(Collectors.toList());
    }

}
