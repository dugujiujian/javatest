package com.dugu.test.service.performance.domain;

import lombok.Getter;

/**
 * @author cihun
 * @date 2022-07 23 10:01:23
 */
public enum ProcessCodeEnum {
    prepare("未开始"),
    objectives_entry("目标录入"),
    objectives_approval("目标审批"),
    objectives_running("目标执行"),
    employee_score("自评"),
    invite_score("同事互评"),
    result_approve("上级评分"),
    result_interview("结果沟通"),
    result_public("结果公开"),
    end("考核结束"),
    cancel("已取消"),
    ;
    @Getter
    public String desc;

    ProcessCodeEnum(String desc) {
        this.desc = desc;
    }
}
