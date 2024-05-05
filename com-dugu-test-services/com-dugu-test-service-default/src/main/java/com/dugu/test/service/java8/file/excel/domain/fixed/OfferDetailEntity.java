package com.dugu.test.service.java8.file.excel.domain.fixed;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author cihun
 * @date 2024/5/5 16:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OfferDetailEntity {

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "报价日期")
    private String offerDate;

    @ApiModelProperty(value = "报价有效期")
    private String offerValidDate;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "客户地址")
    private String customerAddress;

    @ApiModelProperty(value = "联系方式")
    private String contact;

    @ApiModelProperty(value = "合计数量")
    private Integer totalQty;

    @ApiModelProperty(value = "合计金额")
    private BigDecimal totalAmount;
}
