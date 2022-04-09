package com.dugu.test.service.java8.function.supplier;

import com.dugu.test.service.java8.function.supplier.domain.Student;
import com.dugu.test.service.java8.function.supplier.factory.Product;

import java.util.function.Supplier;

/**
 * @Description TODO
 * @Author by cihun
 * @Date 2020/6/20 11:13 AM
 */
public class SupplierDemo {

    public static String getStudentName(Supplier<Student> supplier) {
        if (supplier != null) {
            return supplier.get().getName();
        }
        return null;
    }

    public static double getPrice(Supplier<? extends Product> supplier) {
        return supplier.get().showPrice();
    }
}
