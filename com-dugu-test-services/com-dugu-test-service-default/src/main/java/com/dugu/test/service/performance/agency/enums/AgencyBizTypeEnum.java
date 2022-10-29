package com.dugu.test.service.performance.agency.enums;

import lombok.Getter;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2022/10/29 19:06
 */
public enum AgencyBizTypeEnum {

    WAITING_SET("待设置"),
    OBJECTIVE_APPROVAL("目标审批"), //是否需要区分自定义
    EMPLOYEE_SCORE("自评"),
    INVITE_SCORE("邀请人代办"),
    INVITEE_SCORE("被邀请人评分代办"),
    LEADER_SCORE("上级评分代办"), //是否需要区分自定义
    ;


    @Getter
    private String desc;

    AgencyBizTypeEnum(String desc) {
        this.desc = desc;
    }
}
