package com.dugu.test.service.oop.lsp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cihun
 * @date 2022-08-31 9:51 上午
 */
@Setter
@Getter
public class Son extends Father {
    public void map(Map hashMap) {
        System.out.println("i'm son method");
    }
}
