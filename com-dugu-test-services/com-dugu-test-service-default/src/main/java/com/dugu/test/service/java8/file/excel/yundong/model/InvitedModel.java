package com.dugu.test.service.java8.file.excel.yundong.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 评分人模型
 *
 * @author cihun
 * @date 2023/1/10 15:27
 */

@Getter
@Setter
@ToString
public class InvitedModel {

    /**
     * 评分人姓名
     */
    private String invitedUserName;

    /**
     * 评分人手机
     */
    private String invitedPhone;

    /**
     * 权重
     */
    private String invitedWeight;
}
