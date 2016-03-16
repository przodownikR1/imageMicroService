/*
 * Copyright 2016 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech;

import java.io.File;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Slf4j
public class BootRest {
    private final static String ANONYMOUS = "anonymous";

    public static String ROOT = "upload-dir";

    @Bean
    CommandLineRunner start() {
        return (String[] args) -> {
            new File(ROOT).mkdir();
        };
    }

    @PostConstruct
    public void init() {
        IntStream.range(0, 10).forEach(index -> {
            log.info("++++ {}", index);
        });
    };

    public static void main(String[] args) {
        SpringApplication.run(BootRest.class, args);

    }
}
