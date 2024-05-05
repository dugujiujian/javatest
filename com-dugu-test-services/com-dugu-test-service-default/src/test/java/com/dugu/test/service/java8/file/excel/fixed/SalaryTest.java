package com.dugu.test.service.java8.file.excel.fixed;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.dugu.test.service.java8.file.excel.domain.fixed.StaffSalaryEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cihun
 * @date 2024/5/5 16:22
 */
public class SalaryTest {

    @Test
    public void salaryList() {
        // 模板文件路径
        String templateFilePath = "/Users/cihun/Documents/person/excel/gzb-template.xlsx";
        // 输出文件路径
        String outFilePath = "/Users/cihun/Documents/person/excel/gzb.xlsx";

        // 创建 ExcelWriter 实例
        ExcelWriter writer = EasyExcel
                // 写入到
                .write(outFilePath)
                // 指定模板
                .withTemplate(templateFilePath)
                .build();

        WriteSheet sheet = EasyExcel.writerSheet().build();

        // 获取员工工资数据
        List<StaffSalaryEntity> staffSalaryEntities = getStaffSalaryEntities();

        FillConfig fillConfig = FillConfig.builder()
                // 开启填充换行
                .forceNewRow(true)
                .build();

        // 执行填充操作
        writer.fill(staffSalaryEntities, fillConfig, sheet);

        // 结束
        writer.finish();
    }

    public List<StaffSalaryEntity> getStaffSalaryEntities() {
        List<StaffSalaryEntity> list = new ArrayList<>();

        list.add(StaffSalaryEntity.builder()
                .name("米大傻")
                .post("开发")
                .mouthSalary(new BigDecimal(1320))
                .hourSalary(new BigDecimal("7.59"))
                .shouldAttend(21.0)
                .actualAttend(21.0)
                .overtime(21.0)
                .weekOvertime(8.0)
                .holiday(0.0)
                .normalSalary(new BigDecimal(1320))
                .overtimeSalary(new BigDecimal("238.97"))
                .weekOvertimeSalary(new BigDecimal("242.76"))
                .holidaySalary(new BigDecimal(0))
                .postSubsidy(new BigDecimal(0))
                .award(new BigDecimal(20))
                .deduction(new BigDecimal(0))
                .social(new BigDecimal("113.6"))
                .shouldSalary(new BigDecimal("1688.12"))
                .selfTax(new BigDecimal(0))
                .actualSalary(new BigDecimal("1688.1"))
                .build());


        list.add(StaffSalaryEntity.builder()
                .name("曹大力")
                .post("店长")
                .mouthSalary(new BigDecimal(13200))
                .hourSalary(new BigDecimal("7.59"))
                .shouldAttend(21.0)
                .actualAttend(21.0)
                .overtime(21.0)
                .weekOvertime(8.0)
                .holiday(0.0)
                .normalSalary(new BigDecimal(1320))
                .overtimeSalary(new BigDecimal("238.97"))
                .weekOvertimeSalary(new BigDecimal("242.76"))
                .holidaySalary(new BigDecimal(0))
                .postSubsidy(new BigDecimal(0))
                .award(new BigDecimal(20))
                .deduction(new BigDecimal(0))
                .social(new BigDecimal("113.6"))
                .shouldSalary(new BigDecimal("13200.12"))
                .selfTax(new BigDecimal(0))
                .actualSalary(new BigDecimal("13200.1"))
                .build());

        list.add(StaffSalaryEntity.builder()
                .name("张大仙")
                .post("经理")
                .mouthSalary(new BigDecimal(13200))
                .hourSalary(new BigDecimal("7.59"))
                .shouldAttend(21.0)
                .actualAttend(21.0)
                .overtime(21.0)
                .weekOvertime(8.0)
                .holiday(0.0)
                .normalSalary(new BigDecimal(1320))
                .overtimeSalary(new BigDecimal("238.97"))
                .weekOvertimeSalary(new BigDecimal("242.76"))
                .holidaySalary(new BigDecimal(0))
                .postSubsidy(new BigDecimal(0))
                .deduction(new BigDecimal(0))
                .social(new BigDecimal("113.6"))
                .shouldSalary(new BigDecimal("13200.12"))
                .selfTax(new BigDecimal(0))
                .actualSalary(new BigDecimal("13200.1"))
                .build());

        return list;
    }
}
