package com.dugu.test.service.java8.list.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/13 11:09 AM
 */
@Setter
@Getter
@ToString
public class Apple {

    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }
}
