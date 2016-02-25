package pl.java.scalatech.tools;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;

import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class GenerateRandomStrings {

            
        public static String randomString(Random r, char from, char to, int length) {
            return r.ints(from, to + 1)
                .limit(length)
                .mapToObj(x -> Character.toString((char)x))
                .collect(joining());
        }
        
        
        public static Map<String, Long> countWords(Stream<String> names) {
            return names.collect(groupingBy(name -> name, counting()));
        }
}
