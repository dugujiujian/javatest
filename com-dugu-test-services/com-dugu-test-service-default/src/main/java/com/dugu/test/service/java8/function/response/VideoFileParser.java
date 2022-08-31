package com.dugu.test.service.java8.function.response;

/**
 * @author cihun
 * @date 2022-08-30 11:31 下午
 */
public class VideoFileParser extends AbstractFileParser {
    @Override
    public String parse( File file ) {
        if ( file.getType() == File.Type.VIDEO ) {
            return "Video file: " + file.getContent();
        } else if (next != null) {
            return next.parse( file );
        } else {
            throw new RuntimeException( "Unknown file: " + file );
        }
    }
}