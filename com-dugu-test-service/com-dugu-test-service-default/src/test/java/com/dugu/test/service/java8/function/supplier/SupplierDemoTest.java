package com.dugu.test.service.java8.function.supplier;

import com.dugu.test.service.java8.function.supplier.domain.Student;
import com.dugu.test.service.java8.function.supplier.factory.LiangYouProduct;
import org.junit.Test;

import javax.swing.text.html.Option;

import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * @Description TODO
 * @Author by zhangliping
 * @Date 2020/6/20 11:15 AM
 */
public class SupplierDemoTest {

    @Test
    public void getStudentName() {
        Student stu = new Student();
        stu.setName("dugu");
        stu.setClassNo(50332);

        System.out.println(SupplierDemo.getStudentName(() -> stu));
        stu.setName("dugu2");
        System.out.println(SupplierDemo.getStudentName(() -> stu));
    }

    @Test
    public void getStudentName2() {
        System.out.println(SupplierDemo.getStudentName(Student::new));
    }

    @Test
    public void getPrice() {
        System.out.println(SupplierDemo.getPrice(LiangYouProduct::new));
        System.out.println(SupplierDemo.getPrice(LiangYouProduct::new));
    }
}