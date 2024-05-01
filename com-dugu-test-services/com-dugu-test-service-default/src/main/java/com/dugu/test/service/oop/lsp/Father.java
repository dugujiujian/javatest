package com.dugu.test.service.oop.lsp;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 *
 * @author cihun
 * @date 2022-08-31 9:49 上午
 */
@Getter
@Setter
public class Father {
    public void map(HashMap hashMap) {
        System.out.println("i'm father method");
    }
}
