package com.dugu.test.service.performance.message.domain.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 文档操作行为
 *
 * @author cihun
 * @date 2022-07-27 5:16 下午
 */
public enum DocActionEnum {

    common("公共行为"),
    objectives_entry("目标录入"),
    objectives_approval("目标审批"),
    employee_score("自评"),
    invite_score("同事互评"),
    result_approve("上级评分"),
    result_interview("结果沟通"),
    result_public("结果公开"),
    ;
    @Getter
    public String desc;

    DocActionEnum(String desc) {
        this.desc = desc;
    }


    public static DocActionEnum getByNameIgnoreCase(String name, Boolean exception) {
        if (StringUtils.isNotBlank(name)) {
            for (DocActionEnum item : DocActionEnum.values()) {
                if (item.name().equalsIgnoreCase(name)) {
                    return item;
                }
            }
        }
        return null;
    }
}
