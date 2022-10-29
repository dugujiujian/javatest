package com.dugu.test.service.performance.agency;

import com.dugu.test.service.performance.agency.domain.AgencyRequest;
import com.dugu.test.service.performance.agency.domain.AgencyResponse;
import com.dugu.test.service.performance.agency.domain.AgencyResult;
import com.dugu.test.service.performance.agency.enums.TaskActionTypeEnum;
import com.dugu.test.service.performance.message.PfmDocMessageHandler;
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
public abstract class AbsAgencyHandler implements AgencyService {

    @Override
    public AgencyResult execute(AgencyRequest request, AgencyResponse response) {
        // 1. 参数校验
        validParam(request, response);
        // 2. 数据初始化
        init(request, response);
        // 3. 业务处理

        if (TaskActionTypeEnum.CREATE.equals(request.getTaskActionTypeEnum())) {
            return create(request, response);
        } else if (TaskActionTypeEnum.RETURN_BACK.equals(request.getTaskActionTypeEnum())) {
            return returnBack(request, response);
        } else if (TaskActionTypeEnum.PASS.equals(request.getTaskActionTypeEnum())) {
            return pass(request, response);
        } else if (TaskActionTypeEnum.REVOKE.equals(request.getTaskActionTypeEnum())) {
            return revoke(request, response);
        } else if (TaskActionTypeEnum.TURN_OVER.equals(request.getTaskActionTypeEnum())) {
            return turnOver(request, response);
        }
        return new AgencyResult();
    }

    protected void validParam(AgencyRequest request, AgencyResponse response) {
        if (request == null || response == null) {
            throw new RuntimeException("参数错误");
        }

        if (StringUtils.isEmpty(request.getPlanId())) {
            throw new RuntimeException("参数错误");
        }

        if (request.getContext() == null || StringUtils.isEmpty(request.getAppId())) {
            throw new RuntimeException("参数异常");
        }

        if (request.getTaskActionTypeEnum() == null) {
            throw new RuntimeException("外部参数不能为空");
        }
    }

    /**
     * 数据初始化
     *
     * @param request  输入参数
     * @param response 输出参数
     */
    protected void init(AgencyRequest request, AgencyResponse response) {
        // 计划信息
        // 模版信息
        // 当前节点配置
    }

    /**
     * 创建任务
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    protected abstract AgencyResult create(AgencyRequest request, AgencyResponse response);

    /**
     * 通过任务
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    protected abstract AgencyResult pass(AgencyRequest request, AgencyResponse response);

    /**
     * 退回/撤销
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    protected abstract AgencyResult returnBack(AgencyRequest request, AgencyResponse response);


    /**
     * 撤销
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    protected abstract AgencyResult revoke(AgencyRequest request, AgencyResponse response);

    /**
     * 撤销
     *
     * @param request  输入参数
     * @param response 输出参数
     * @return {@link AgencyResult}
     */
    protected abstract AgencyResult turnOver(AgencyRequest request, AgencyResponse response);
}
