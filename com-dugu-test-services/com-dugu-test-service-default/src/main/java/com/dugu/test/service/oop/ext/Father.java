package com.dugu.test.service.oop.ext;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cihun
 * @date 2022-08-31 9:49 上午
 */
@Getter
@Setter
public class Father {
    private String name;
    private Integer age=38;

    public void resetAge() {
        age = 28;
        System.out.println(this.getClass().getSimpleName() + ".age=" + age);
    }
}
