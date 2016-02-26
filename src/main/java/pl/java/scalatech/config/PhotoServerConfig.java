package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
@Configuration
@ComponentScan(basePackages="pl.java.scalatech.service")
public class PhotoServerConfig {
    @Bean
    Resource picture() {
        return new org.springframework.core.io.ClassPathResource("new_mg.png");
    }

}
