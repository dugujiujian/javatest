package com.dugu.test.service.performance.msg;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cihun
 * @date 2022-07-24 12:08 上午
 */
@Service
public class DocMessageSendStrategyContext {

    @Autowired
    private final Map<String, DocMessageStrategy> strategyMap = new ConcurrentHashMap<>();

    public DocMessageSendStrategyContext(Map<String, DocMessageStrategy> strategyMap) {
        strategyMap.forEach(this.strategyMap::put);
    }

    public DocMessageStrategy getMessageStrategy(ProcessCodeEnum processCode) {
        if (processCode == null) {
            return null;
        }
        return strategyMap.get(processCode.name());
    }


}
