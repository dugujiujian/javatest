package com.dugu.test.service.lst.rule;


import com.dugu.test.service.lst.rule.domain.RuleDefinitionInfo;
import lombok.Data;

/**
 * @author cihun
 * @date 2022-02-23 3:24 下午
 */

@Data
public class AbstractBaseRuleDefinition<T> implements BaseRuleDefinition {

    /**
     * 获取当前规则的描述定义
     */
    protected RuleDefinitionInfo ruleDefinitionInfo;

}
