package com.dugu.test.service.performance.factory;

import com.dugu.test.service.performance.domain.PfmDocDTO;
import com.dugu.test.service.performance.domain.PfmPlanDTO;
import com.dugu.test.service.performance.domain.PfmTemplateDTO;
import com.dugu.test.service.performance.domain.model.ProcessCodeEnum;
import com.dugu.test.service.performance.domain.model.TemplateNodeConfig;
import com.dugu.test.service.performance.domain.request.DocProcessRequest;
import com.dugu.test.service.performance.domain.response.DocProcessResponse;
import org.springframework.stereotype.Component;

/**
 * @author cihun
 * @date 2022-07-23 10:26 上午
 */
@Component
public abstract class AbsDocProcessProcessor implements DocProcessProcessor {

    @Override
    public boolean init(DocProcessRequest request, DocProcessResponse response) {
        System.out.println(getCurProcessCode() + ":初始化");
        // 文档信息获取
        response.setDoc(new PfmDocDTO());
        // 计划信息获取
        response.setPlan(new PfmPlanDTO());
        // 模版
        response.setTemplate(new PfmTemplateDTO());
        // 当前节点模版配置
        response.setCurConfig(new TemplateNodeConfig());
        // 下一个节点模版配置
        response.setNextConfig(new TemplateNodeConfig());
        // 下一个节点
        response.setNextProcessCode(ProcessCodeEnum.objectives_approval);
        return true;
    }

    @Override
    public boolean execute(DocProcessRequest request, DocProcessResponse response) {
        System.out.println(getCurProcessCode() + ":开始编排");
        if (request == null || response == null) {
            return false;
        }
        if (init(request, response)) {
            boolean result = doHandler(response);
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
    abstract public boolean doHandler(DocProcessResponse response);

    /**
     * 后置处理
     *
     * @param response 输出
     * @return
     */
    @Override
    abstract public boolean after(DocProcessResponse response);
}
