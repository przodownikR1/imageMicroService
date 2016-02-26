/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.java.scalatech.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


@Configuration
@ComponentScan(basePackages= {"pl.java.scalatech.service","pl.java.scalatech.repository","pl.java.scalatech.web"},
includeFilters= {@ComponentScan.Filter(type=FilterType.ANNOTATION,value= {Controller.class,RestController.class,Service.class,Component.class})})

@EnableJpaRepositories(basePackages="pl.java.scalatech.repository")

@EntityScan(basePackages="pl.java.scalatech.domain")
//@EnableAutoConfiguration
@Import({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
    DispatcherServletAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class,
    ServerPropertiesAutoConfiguration.class,EmbeddedServletContainerAutoConfiguration.class})
@Profile("test")
public class JpaWebConfig {

    
    
    
    
}
