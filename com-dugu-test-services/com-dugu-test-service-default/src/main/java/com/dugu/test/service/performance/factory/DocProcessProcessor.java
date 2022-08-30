package com.dugu.test.service.performance.factory;

import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;

/**
 * @author cihun
 * @date 2022-07-23 10:10 上午
 */
public interface DocProcessProcessor {

    /**
     * 编排
     *
     * @param request  请求参数
     * @param response 输出
     * @return true=处理成功
     */
    boolean execute(DocProcessRequest request, DocProcessResponse response);

    /**
     * 前置方法
     *
     * @param request  请求参数
     * @param response 输出
     * @return true=处理成功
     */
    boolean init(DocProcessRequest request, DocProcessResponse response);

    /**
     * 数据处理
     *
     * @param response 输出
     * @return true=处理成功
     */
    boolean doHandler(DocProcessResponse response);

    /**
     * 后置方法
     *
     * @param response 输出
     * @return true=处理成功
     */
    boolean after(DocProcessResponse response);


    /**
     * 当前状态
     *
     * @return {@link ProcessCodeEnum}
     */
    ProcessCodeEnum getCurProcessCode();

}
