package com.dugu.test.service.oop.lsp;

/**
 * 协变
 *
 * <pre>
 *     父类型→子类型：越来越具体specific 返回值类型：不变或变得更具体 异常的类型
 * </pre>
 *
 * @author cihun
 * @date 2022-08-31 4:10 下午
 */
public class Covariant {

    class T {
        Object a() {
            return new Object();
        }
    }

    class S extends T {
        @Override
        String a() {
            return "";
        }
    }

    public static void main(String[] args) {

    }

}
