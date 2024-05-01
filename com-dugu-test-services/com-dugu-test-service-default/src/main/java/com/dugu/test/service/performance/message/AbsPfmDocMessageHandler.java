package com.dugu.test.service.performance.message;

import com.dugu.test.service.performance.message.domain.PfmDocMsgBatchSendRequest;
import com.dugu.test.service.performance.message.domain.PfmDocMsgRequest;
import com.dugu.test.service.performance.message.domain.PfmDocMsgResponse;
import com.dugu.test.service.performance.message.domain.PfmDocMsgSingleSendRequest;
import com.dugu.test.service.performance.message.domain.enums.DocActionEnum;
import com.dugu.test.service.performance.message.domain.enums.ProcessCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 消息抽象类
 *
 * @author cihun
 * @date 2022-07-26 10:48 上午
 */
@Component
public abstract class AbsPfmDocMessageHandler<S extends ProcessCodeEnum,E extends DocActionEnum> implements PfmDocMessageHandler<S,E> {

    @Override
    public boolean sendMessage(PfmDocMsgRequest request, PfmDocMsgResponse response) {
        // 1. 参数校验
        validParam(request, response);
        // 2. 数据初始化
        init(request, response);
        // 3. 业务处理
        boolean result;
        if (request instanceof PfmDocMsgBatchSendRequest) {
            result = doBatchHandle((PfmDocMsgBatchSendRequest) request, response);
        } else {
            result = doHandle((PfmDocMsgSingleSendRequest) request, response);
        }
        if (result) {
            msgLog(response);
        }
        return result;
    }

    protected void validParam(PfmDocMsgRequest request, PfmDocMsgResponse response) {
        if (request == null || StringUtils.isEmpty(request.getPlanId())
                || response == null) {
            throw new RuntimeException("参数错误");
        }
        if (request.getContext() == null || StringUtils.isEmpty(request.getAppId())) {
            throw new RuntimeException("参数异常");
        }
        if(request.getUseOuterInitObject()!=null && request.getUseOuterInitObject()){
            if(response.getPlan()==null ||
                    response.getTemplate()==null || response.getProcessConfig()==null){
                throw new RuntimeException("外部参数不能为空");
            }
        }
    }

    /**
     * 数据初始化
     *
     * @param request
     * @param response
     */
    protected void init(PfmDocMsgRequest request, PfmDocMsgResponse response) {
        // 计划信息
        // 模版信息
        // 当前节点配置
    }

    /**
     * 批量数据处理
     *
     * @param request
     * @param response
     * @return
     */
    protected abstract boolean doBatchHandle(PfmDocMsgBatchSendRequest request, PfmDocMsgResponse response);

    /**
     * 单体数量处理
     *
     * @param request
     * @param response
     * @return
     */
    protected abstract boolean doHandle(PfmDocMsgSingleSendRequest request, PfmDocMsgResponse response);

    /**
     * 消息日志
     *
     * @param response
     * @return
     */
    protected  void msgLog(PfmDocMsgResponse response){
        //消息日志入库
    }

    /**
     * 当前状态
     *
     * @return
     */
    protected abstract S onProcessCode();

}
