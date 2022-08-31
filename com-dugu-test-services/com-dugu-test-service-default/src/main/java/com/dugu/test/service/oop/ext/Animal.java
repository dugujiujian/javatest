package com.dugu.test.service.oop.ext;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cihun
 * @date 2022-08-31 10:02 上午
 */
@Getter
@Setter
public class Animal {
    String eys="眼睛";
    String name="无名";

    public void eat(){
        System.out.println("动物吃东西!");
    }
}
