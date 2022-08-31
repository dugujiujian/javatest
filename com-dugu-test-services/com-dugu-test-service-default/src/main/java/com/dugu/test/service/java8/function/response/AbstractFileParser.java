package com.dugu.test.service.java8.function.response;

/**
 * @author cihun
 * @date 2022-08-30 11:30 下午
 */
public abstract class AbstractFileParser implements FileParser {
    protected FileParser next;

    @Override
    public void setNextParser( FileParser next ) {
        this.next = next;
    }
}
