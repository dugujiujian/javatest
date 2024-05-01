package com.dugu.test.service.lst.data;

import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;

/**
 * @author cihun
 * @date 2022-08-31 2:18 下午
 */
public interface DataHandler {

    /**
     * 数据处理
     *
     * @param rowModel         数据杭
     * @param handlerResponse  输出模型
     * @param dataHandlerChain 数据处理链
     */
    void handle(RowModel rowModel, HandlerResponse handlerResponse, DataHandlerChain dataHandlerChain);

    /**
     * 错误信息处理
     *
     * @param rowModel        数据行
     * @param handlerResponse 输出模型
     */
    void onException(RowModel rowModel, HandlerResponse handlerResponse);

    /**
     * 排序
     *
     * @return 序号
     */
    int getOrder();
}
