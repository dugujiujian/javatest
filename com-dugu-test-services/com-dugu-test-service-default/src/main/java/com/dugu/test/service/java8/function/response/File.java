package com.dugu.test.service.java8.function.response;

/**
 * @author cihun
 * @date 2022-08-30 11:29 下午
 */
public class File {
    enum Type { TEXT, AUDIO, VIDEO }

    private final Type type;
    private final String content;

    public File( Type type, String content ) {
        this.type = type;
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return type + ": " + content;
    }
}
