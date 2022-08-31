package com.dugu.test.service.java8.function.response;

import java.util.Optional;

/**
 * @author cihun
 * @date 2022-08-30 11:32 下午
 */
public class ParseUtil {
    public static Optional<String> parseText(File file) {
        return file.getType() == File.Type.TEXT ?
                Optional.of("Text file: " + file.getContent()) :
                Optional.empty();
    }

    public static Optional<String> parseAudio(File file) {
        return file.getType() == File.Type.AUDIO ?
                Optional.of("Audio file: " + file.getContent()) :
                Optional.empty();
    }

    public static Optional<String> parseVideo(File file) {
        return file.getType() == File.Type.VIDEO ?
                Optional.of("Video file: " + file.getContent()) :
                Optional.empty();
    }

}
