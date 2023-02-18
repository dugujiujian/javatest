package com.dugu.test.service.lst.rule;

import java.util.List;

/**
 * 规则定义工厂
 *
 * @param <PK>
 * @param <TYPE>
 * @param <RuleDefinition>
 * @author cihun
 * @date 2022-02-23 3:26 下午
 */
public interface RuleDefinitionFactory<PK, TYPE, RuleDefinition extends BaseRuleDefinition> {

    /**
     * 查询所有数据
     *
     * @return
     */
    List<RuleDefinition> queryAll();

    /**
     * 根据类型和主键查询
     *
     * @param type
     * @param pk
     * @return
     */
    default RuleDefinition query(TYPE type, PK pk) {
        throw new UnsupportedOperationException();
    }
}
