package com.dugu.test.service.java8.file.excel.yundong;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.dugu.test.service.java8.file.excel.ExcelUtil;
import com.dugu.test.service.java8.file.excel.yundong.model.AlbUserInfo;
import com.dugu.test.service.java8.file.excel.yundong.model.InvitedDemoModel;
import org.junit.Test;

/**
 * @author cihun
 * @date 2023/1/10 15:06
 */
public class AlabiExcelTest {

    /**
     * 转成云动格式
     */
    @Test
    public void toYunDongTemplate() {
        // 1. 竖转行
        ExcelReader excelReader = EasyExcel.read(ExcelUtil.ALB_EXCEL_PATH, AlbUserInfo.class, new AlabiDataListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        // 2. 获取用户信息写入txt
        ExcelReader  excelReader2 = EasyExcel.read(ExcelUtil.YUN_DONG_TEMPLATE_PATH, InvitedDemoModel.class, new AlabiDataTempUserListener()).build();
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        readSheet = EasyExcel.readSheet(0).build();
        excelReader2.read(readSheet);
        excelReader2.finish();
        // 3. 聚合模版
        ExcelReader   excelReader3 = EasyExcel.read(ExcelUtil.SPAN_TEMPLATE_PATH, InvitedDemoModel.class, new AlabiDataFinalListener()).build();
        readSheet = EasyExcel.readSheet(0).build();
        excelReader3.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader3.finish();

    }


}