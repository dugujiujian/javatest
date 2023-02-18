package com.dugu.test.service.lst.rule.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhaohaihua
 */
@Data
public class RuleDefinitionInfo implements Serializable {
    /**
     * 规则ID
     */
    private Integer ruleId;
    /**
     * 规则类型
     */
    private Integer ruleType;
    /**
     * 参与者定义ID
     */
    private Integer actorDefinitionId;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则描述
     */
    private String description;


}
