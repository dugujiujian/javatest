package com.dugu.test.service.oop.lsp;

/**
 * @author cihun
 * @date 2022-08-31 12:29 下午
 */

public class Square extends Rectangle {

    private int height;
    private int width;

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.setWidth(height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.setWidth(height);
    }



}
