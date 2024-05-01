package com.dugu.test.service.lst.rule;

import com.dugu.test.service.lst.rule.domain.RuleDefinitionInfo;

/**
 * @author cihun
 * @date 2022-02-23 3:24 下午
 */
public interface BaseRuleDefinition {
    /**
     * 获取规则定义
     *
     * @return {@link RuleDefinitionInfo}
     */
    RuleDefinitionInfo getRuleDefinitionInfo();
}
