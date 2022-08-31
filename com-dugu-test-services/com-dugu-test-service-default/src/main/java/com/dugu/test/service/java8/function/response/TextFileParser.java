package com.dugu.test.service.java8.function.response;

/**
 * @author cihun
 * @date 2022-08-30 11:30 下午
 */
public class TextFileParser extends AbstractFileParser {
    @Override
    public String parse( File file ) {
        if ( file.getType() == File.Type.TEXT ) {
            return "Text file: " + file.getContent();
        } else if (next != null) {
            return next.parse( file );
        } else {
            throw new RuntimeException( "Unknown file: " + file );
        }
    }
}