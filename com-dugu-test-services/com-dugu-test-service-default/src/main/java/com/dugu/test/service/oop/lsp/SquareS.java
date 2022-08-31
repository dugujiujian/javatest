package com.dugu.test.service.oop.lsp;

/**
 * @author cihun
 * @date 2022-08-31 12:42 下午
 */
public class SquareS implements Shape {

    private int length;

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getHeight() {
        return length;
    }

    @Override
    public int getWidth() {
        return length;
    }


}
