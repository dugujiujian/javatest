package com.dugu.test.service.performance.inf;

import com.dugu.test.service.performance.domain.DocProcessRequest;
import com.dugu.test.service.performance.domain.DocProcessResponse;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 10:26 上午
 */
@Component
public abstract class AbsDocProcessProcessor implements DocProcessProcessor {


    @Override
    public boolean init(DocProcessRequest request, DocProcessResponse response) {
        System.out.println(getCurProcessCode() + "初始化...");
        // 文档信息获取
        // 计划信息获取
        // 配置获取
        return true;
    }

    @Override
    public boolean execute(DocProcessRequest request, DocProcessResponse response) {
        System.out.println(getCurProcessCode() + "开始处理...");
        if (request == null || response == null) {
            return false;
        }
        if (init(request, response)) {
            boolean result = handle(response);
            if (result) {
                return after(response);
            }
        }
        return false;
    }

    /**
     * 当前业务功能处理
     *
     * @param response 输出
     * @return
     */
    @Override
    abstract public boolean handle(DocProcessResponse response);

    /**
     * 后置处理
     *
     * @param response 输出
     * @return
     */
    @Override
    abstract public boolean after(DocProcessResponse response);
}
