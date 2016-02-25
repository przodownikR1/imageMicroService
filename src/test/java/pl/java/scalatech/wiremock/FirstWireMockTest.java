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
package pl.java.scalatech.wiremock;
/*import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;*/

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/*import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;*/
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class FirstWireMockTest {
    
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
    
   /* @Rule
    static public WireMockRule wireMockRule = new WireMockRule(8089); // No-args constructor defaults to port 8080
   
    @AfterClass
    public static void stopWireMock() {
        wireMockRule.shutdownServer();
    }
    @Test
    public void configure_static_client() {
        WireMock.configureFor(8080);
        assertThat(true).isTrue();
    }

    @Test
    public void configure_standard_client() {
        WireMock wireMock = new WireMock(8089);
        assertThat(wireMock.allStubMappings().getMappings()).isNotNull().isEmpty();
    }
    
    @Test
    public void shouldIdentifyCepAsValid(){
        stubFor(get(urlEqualTo("/customer/2"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("\"{ "+
                                "\"user\": \"przodownik\"}\"")));
        
       
    }
    
    @Test
    public void exampleTest() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("<response>noodles burger cakes strudles</response>")));

        // can't use jersey 2 client...
    }*/
    
  /*  @Test
    public void exampleTest() {
        stubFor(get(urlEqualTo("/my/resource"))
                .withHeader("Accept", equalTo("text/xml"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "text/xml")
                    .withBody("<response>Some content</response>")));

        Result result = myHttpServiceCallingObject.doSomething();

        assertTrue(result.wasSuccessFul());

        verify(postRequestedFor(urlMatching("/my/resource/[a-z0-9]+"))
                .withRequestBody(matching(".*<message>1234</message>.*"))
                .withHeader("Content-Type", notMatching("application/json")));
    }*/
}
