package com.dugu.test.service.java8.file.excel.yundong.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 评分人模型
 *
 * @author cihun
 * @date 2023/1/10 15:29
 */
@Getter
@Setter
@ToString
public class InvitedYundongModel {

    /**
     * 姓名
     */
    private String userName;

    /**
     * 评分用户
     */
    private List<InvitedModel> invitedModelList;
}
