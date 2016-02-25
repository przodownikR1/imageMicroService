package pl.java.scalatech.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = { "pl.java.scalatech.web" })
public class RestConfiguration extends RepositoryRestConfigurerAdapter {
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        //config.exposeIdsFor(User.class);
        config.setBasePath("/api");
        config.setDefaultPageSize(15);        
    }
        
    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v){       
    }
    

}