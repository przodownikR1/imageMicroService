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
package pl.java.scalatech.rest;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import pl.java.scalatech.config.JpaWebConfig;


@SpringApplicationConfiguration(classes = JpaWebConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port:9090")
public abstract class AbstractControllerRestTest {
    RestTemplate template = new TestRestTemplate();

    @Autowired
    protected WebApplicationContext webApplicationContext; //without this not mapping controllers

    protected MockMvc mockMvc;



    @Before
    public void getMockMvc() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }


}
