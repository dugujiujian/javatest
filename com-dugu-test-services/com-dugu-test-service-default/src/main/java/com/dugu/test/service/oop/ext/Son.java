package com.dugu.test.service.oop.ext;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cihun
 * @date 2022-08-31 9:51 上午
 */
@Setter
@Getter
public class Son extends Father{

    private Integer age;

    @Override
    public void resetAge() {
        super.resetAge();
        age = 18;
        System.out.println(this.getClass().getSimpleName() + ".age=" + age);
        System.out.println("value=" + age);
        System.out.println("Father.age=" + super.getAge());
    }
}
