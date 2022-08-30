package com.dugu.test.service.performance.message.domain.enums;

import lombok.Getter;

/**
 * 状态位定义
 *
 * @author jiuyi
 * @date 2021/12/15 10:14 AM
 */
public enum ProcessCodeEnum {
    prepare("未开始"),
    start("已启动"),
    objectives_entry("目标录入"),
    objectives_approval("目标审批"),
    objectives_running("目标执行"),
    employee_score("自评"),
    invite_score("同事互评"),
    result_approve("上级评分"),
    result_interview("结果沟通"),
    result_public("结果公开"),
    end("考核结束"),
    force_end("强制结束考核计划"),
    cancel("已取消"),
    deleted("已删除"),
    /**
     * 已启用, 试用期考核计划状态
     */
    enabled("已启用"),
    /**
     * 已禁用, 试用期考核计划状态
     */
    disabled("已禁用"),
    ;
    @Getter
    public String desc;

    ProcessCodeEnum(String desc) {
        this.desc = desc;
    }

}
