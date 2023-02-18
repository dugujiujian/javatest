package com.dugu.test.service.lst.data;

import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;

/**
 * @author cihun
 * @date 2022-08-31 2:31 下午
 */
public interface DataService {
    /**
     * 数据处理
     *
     * @param rowModel        数据行
     * @param handlerResponse 输出
     */
    void doHandle(RowModel rowModel, HandlerResponse handlerResponse);
}
