package com.dugu.test.service.lst.data.handler;

import com.dugu.test.service.lst.data.DataHandler;
import com.dugu.test.service.lst.data.DataHandlerChain;
import com.dugu.test.service.lst.data.domain.HandlerResponse;
import com.dugu.test.service.lst.data.domain.RowModel;
import org.springframework.stereotype.Service;

/**
 * @author cihun
 * @date 2022-08-31 2:46 下午
 */
@Service
public class FilterHandler implements DataHandler {

    @Override
    public void handle(RowModel rowModel, HandlerResponse handlerResponse, DataHandlerChain dataHandlerChain) {
        System.out.println(this.getClass().getSimpleName());
        dataHandlerChain.doHandler(rowModel, handlerResponse);
    }

    @Override
    public void onException(RowModel rowModel, HandlerResponse handlerResponse) {

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
