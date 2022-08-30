package com.dugu.test.service.performance.message.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cihun
 * @date 2022-08-07 11:02 下午
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleDTO {
    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 花名
     */
    private String nickName;

    /**
     * 名子花名的拼接
     */
    private String label;

    /**
     * 图像
     */
    private String avatar;

    /**
     * 工号
     */
    private String workNo;

    private String leaderNo;
    private UserSimpleDTO leader;

    private Boolean hasChildren;
    private String position;
    private String positionId;

    private String deptId;


    /**
     * 汇报线
     * 从大到小（包含自己）
     */
    private String hrPath;

    /**
     * 按identityID串起来的
     */
    private String path;
    private Integer depth;

    /**
     * 入职日期
     * 2012-01-01
     */
    private String entryDate;

    /**
     * 是否试用期
     */
    private Boolean probation;


    /**
     * 员工类型
     *
     * see userCenter.IdentityTypeEnum
     */
    private String staffType;

    /**
     * 部门名称
     */
    private String deptName;

    private Long isDeleted;

}
