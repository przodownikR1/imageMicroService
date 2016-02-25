package pl.java.scalatech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
@Configuration
public class PhotoServerConfig {
    @Bean
    Resource picture() {
        return new org.springframework.core.io.ClassPathResource("new_mg.png");
    }

}
