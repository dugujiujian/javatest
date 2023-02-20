package com.dugu.test.service.java8.file.excel.yundong.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 阿拉比用户即评分人信息
 *
 * @author cihun
 * @date 2023/1/10 14:59
 */
@Getter
@Setter
@ToString
public class AlbUserInfo  {

    /**
     * 员工姓名
     */
    @ExcelProperty(value = "员工姓名", index = 0)
    private String userName;
    /**
     * 项目经理
     */
    @ExcelProperty(value = "项目经理", index = 1)
    private String pm;
    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码", index = 2)
    private String pmPhone;
    /**
     * 权重
     */
    @ExcelProperty(value = "权重", index = 3)
    private String weight;
    /**
     * 部门
     */
    @ExcelProperty(value = "部门", index = 4)
    private String department;

}
