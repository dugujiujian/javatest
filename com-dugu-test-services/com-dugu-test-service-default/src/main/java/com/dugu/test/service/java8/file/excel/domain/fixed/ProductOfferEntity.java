package com.dugu.test.service.java8.file.excel.domain.fixed;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author cihun
 * @date 2024/5/5 19:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOfferEntity {

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "型号及规格")
    private String typeSpec;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "备注")
    private String remark;

}
