package pl.java.scalatech.tools;

import static java.util.stream.IntStream.range;
import static pl.java.scalatech.tools.GenerateRandomStrings.randomString;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class GenerateRandomTest {
    @Test
    public void test(){
        Map<Object,List<String>> result = range(0, 100)
        .mapToObj(i -> randomString(new Random(), 'a', 'z', 10))
        .sorted()
        .collect(Collectors.groupingBy(name -> name.charAt(0)));
        
        log.info("{}",result);
        //.forEach((letter, names) -> System.out.println(letter + "\n\t" + names.stream().collect(joining("\n\t"))));

    }

}
