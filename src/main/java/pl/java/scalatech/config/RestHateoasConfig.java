package pl.java.scalatech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
@Configuration
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
public class RestHateoasConfig {

}
