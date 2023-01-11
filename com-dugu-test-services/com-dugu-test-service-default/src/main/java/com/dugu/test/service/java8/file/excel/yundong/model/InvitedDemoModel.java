package com.dugu.test.service.java8.file.excel.yundong.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author cihun
 * @date 2023/1/10 17:33
 */
@Getter
@Setter
@ToString
public class InvitedDemoModel {
    @ExcelProperty(value = "用户ID(勿动)", index = 0)
    private String userId;

    @ExcelProperty(value = "用户名", index = 1)
    private String userName;

    @ExcelProperty(value = "职位(勿动)", index = 2)
    private String position;

    @ExcelProperty(value = "部门", index = 3)
    private String department;

    @ExcelProperty(value = "评分人1姓名", index = 4)
    private String invitedUserName1;

    @ExcelProperty(value = "评分人1手机", index = 5)
    private String invitedPhone1;

    @ExcelProperty(value = "评分人1权重(%)", index = 6)
    private String invitedWeight1;

    @ExcelProperty(value = "评分人2姓名", index = 7)
    private String invitedUserName2;

    @ExcelProperty(value = "评分人2手机", index = 8)
    private String invitedPhone2;

    @ExcelProperty(value = "评分人2权重(%)", index = 9)
    private String invitedWeight2;

    @ExcelProperty(value = "评分人3姓名", index = 10)
    private String invitedUserName3;

    @ExcelProperty(value = "评分人3手机", index = 11)
    private String invitedPhone3;

    @ExcelProperty(value = "评分人3权重(%)", index = 12)
    private String invitedWeight3;

    @ExcelProperty(value = "评分人4姓名", index = 13)
    private String invitedUserName4;

    @ExcelProperty(value = "评分人4手机", index = 14)
    private String invitedPhone4;

    @ExcelProperty(value = "评分人4权重(%)", index = 15)
    private String invitedWeight4;

    @ExcelProperty(value = "评分人5姓名", index = 16)
    private String invitedUserName5;

    @ExcelProperty(value = "评分人5手机", index = 17)
    private String invitedPhone5;

    @ExcelProperty(value = "评分人5权重(%)", index = 18)
    private String invitedWeight5;

    @ExcelProperty(value = "评分人6姓名", index = 19)
    private String invitedUserName6;

    @ExcelProperty(value = "评分人6手机", index = 20)
    private String invitedPhone6;

    @ExcelProperty(value = "评分人6权重(%)", index = 21)
    private String invitedWeight6;

    @ExcelProperty(value = "评分人7姓名", index = 22)
    private String invitedUserName7;

    @ExcelProperty(value = "评分人7手机", index = 23)
    private String invitedPhone7;

    @ExcelProperty(value = "评分人7权重(%)", index = 24)
    private String invitedWeight7;

    @ExcelProperty(value = "评分人8姓名", index = 25)
    private String invitedUserName8;

    @ExcelProperty(value = "评分人8手机", index = 26)
    private String invitedPhone8;

    @ExcelProperty(value = "评分人8权重(%)", index = 27)
    private String invitedWeight8;

    @ExcelProperty(value = "评分人9姓名", index = 28)
    private String invitedUserName9;

    @ExcelProperty(value = "评分人9手机", index = 29)
    private String invitedPhone9;

    @ExcelProperty(value = "评分人9权重(%)", index = 30)
    private String invitedWeight9;

    @ExcelProperty(value = "评分人10姓名", index = 31)
    private String invitedUserName10;

    @ExcelProperty(value = "评分人10手机", index = 32)
    private String invitedPhone10;

    @ExcelProperty(value = "评分人10权重(%)", index = 33)
    private String invitedWeight10;

}
