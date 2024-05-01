package com.dugu.test.service.lst.data;

import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;

import java.util.List;

/**
 * @author cihun
 * @date 2022-08-31 2:22 下午
 */
public class DataHandlerChain {

    /**
     * 处理器集合
     */
    List<DataHandler> handlers;
    /**
     * 处理器索引下标
     */
    int current = 0;

    public void doHandler(RowModel rowModel, HandlerResponse handlerResponse) {
        current++;
        if (current < handlers.size()) {
            handlers.get(current).handle(rowModel, handlerResponse, this);
        }
    }
}
