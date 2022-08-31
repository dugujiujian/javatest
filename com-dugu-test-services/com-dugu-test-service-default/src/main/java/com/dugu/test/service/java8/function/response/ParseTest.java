package com.dugu.test.service.java8.function.response;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author cihun
 * @date 2022-08-30 11:31 下午
 */
public class ParseTest {

    public static void main(String[] args) {
//        FileParser textParser = new TextFileParser();
//        FileParser audioParser = new AudioFileParser();
//        FileParser videoParser = new VideoFileParser();
//
//
//        textParser.setNextParser( audioParser );
//        audioParser.setNextParser( videoParser );
//
//
//        File file = new File( File.Type.AUDIO, "Dream Theater  - The Astonishing" );
//        String result = textParser.parse( file );
//        System.out.println(result);

        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");

        String result = Stream.<Function<File, Optional<String>>>of(
                ParseUtil::parseText,
                ParseUtil::parseAudio,
                ParseUtil::parseVideo)
                .map(f -> f.apply(file))
                .filter(Optional::isPresent)
                .findFirst()
                .flatMap(Function.identity())
                .orElseThrow(() -> new RuntimeException("Unknown file: " + file));

        System.out.println(result);
    }
}
