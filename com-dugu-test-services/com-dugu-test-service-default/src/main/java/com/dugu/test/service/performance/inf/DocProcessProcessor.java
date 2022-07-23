package com.dugu.test.service.performance.inf;

import com.dugu.test.service.performance.domain.DocProcessRequest;
import com.dugu.test.service.performance.domain.DocProcessResponse;
import com.dugu.test.service.performance.domain.ProcessCodeEnum;

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
    boolean handle(DocProcessResponse response);

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
