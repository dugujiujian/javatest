package com.dugu.test.service.java8.file.excel.fixed;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.dugu.test.service.java8.file.excel.domain.fixed.OfferDetailEntity;
import com.dugu.test.service.java8.file.excel.domain.fixed.ProductOfferEntity;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author cihun
 * @date 2024/5/5 19:09
 */
public class ProductOfferTest {


    @Test
    public void exportByTemplate() throws IOException {

        // 文件模板输入流，将 excel 模板放到 resources 目录下
        InputStream templateFile = new ClassPathResource("/template/excel/bjd-template.xlsx").getInputStream();

        String templateFilePath = "/Users/cihun/Documents/person/excel/bjd-template.xlsx";

        String outFilePath = "/Users/cihun/Documents/person/excel/bjd.xlsx";

        ExcelWriter writer = EasyExcel
                .write(outFilePath)
                .withTemplate(templateFile)
                .build();

        WriteSheet sheet = EasyExcel.writerSheet().build();


        OfferDetailEntity offerDetail = OfferDetailEntity.builder()
                .companyName("多加辣科技")
                .offerDate("2024-04-07")
                .offerValidDate("2024-04-11")
                .customerName("米大傻")
                .customerAddress("广东省广州市")
                .contact("078-182****4568")
                .totalQty(5)
                .totalAmount(new BigDecimal(10300))
                .build();

        // 填充普通占位符
        // 这里 data 使用对象或者 Map 都可以
        writer.fill(offerDetail, sheet);

        List<ProductOfferEntity> list = new ArrayList<>();
        list.add(ProductOfferEntity.builder()
                .productName("电脑")
                .typeSpec("联想")
                .price(new BigDecimal(5000))
                .quantity(2)
                .amount(new BigDecimal(10000))
                .build());
        list.add(ProductOfferEntity.builder()
                .productName("鼠标")
                .typeSpec("联想")
                .price(new BigDecimal(100))
                .quantity(3)
                .amount(new BigDecimal(300))
                .build());

        // 填充配置，开启组合填充换行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();

        // 填充列表占位符
        writer.fill(list, fillConfig, sheet);

        //填充完成
        writer.finish();

    }
}
