package com.dugu.test.service.oop.lsp;

/**
 * @author cihun
 * @date 2022-08-31 12:36 下午
 */
public class Check {

    public  void resize(Rectangle rectangle){
        while (rectangle.getWidth()>=rectangle.getHeight()){
            rectangle.setHeight(rectangle.getHeight()+1);
            System.out.println("长"+rectangle.getHeight()+",宽="+rectangle.getWidth());
        }
        System.out.println("长"+rectangle.getHeight()+",宽="+rectangle.getWidth());
    }

    public static void main(String[] args) {
        Square square=new Square();
        square.setHeight(10);
        Check check=new Check();
        check.resize(square);
    }
}
