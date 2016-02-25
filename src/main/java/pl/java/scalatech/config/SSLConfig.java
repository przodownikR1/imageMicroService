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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource("classpath:/tomcat.https.properties")
@Slf4j
@EnableConfigurationProperties(SSLConfig.TomcatSslConnectorProperties.class)
@Profile("prodSSL")
public class SSLConfig {

    @Bean
    public EmbeddedServletContainerFactory servletContainer(TomcatSslConnectorProperties properties) {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createSslConnector(properties));
        tomcat.setTomcatContextCustomizers(Arrays.asList(new CustomCustomizer(properties)));
        return tomcat;
    }

    static class CustomCustomizer implements TomcatContextCustomizer {
        TomcatSslConnectorProperties properties;
        public CustomCustomizer(TomcatSslConnectorProperties properties) {
         this.properties = properties;
        }
        @Override
        public void customize(Context context) {
            context.setUseHttpOnly(properties.httpOnly);
            context.setSessionTimeout(properties.getTimeout());
        }

    }
    
    private Connector createSslConnector(TomcatSslConnectorProperties properties) {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        try {
            File keystore = new ClassPathResource(properties.keystore).getFile();
            File truststore = new ClassPathResource(properties.keystore).getFile();
            connector.setScheme(properties.scheme);
            connector.setSecure(properties.isSecure());
            connector.setPort(properties.getPort());
            protocol.setSSLEnabled(properties.enable);
            protocol.setKeystoreFile(keystore.getAbsolutePath());
            protocol.setKeystorePass(properties.keystorePassword);
            protocol.setTruststoreFile(truststore.getAbsolutePath());
            protocol.setTruststorePass(properties.keystorePassword);
            protocol.setKeyAlias(properties.keyAlias);
            connector.setRedirectPort(properties.getPort());
            connector.setAllowTrace(properties.allowTrace);
            return connector;
        } catch (IOException ex) {
            throw new IllegalStateException("can't access keystore: [" + "keystore ] or truststore: [" + "keystore" + "]", ex);
        }
    }

    @ConfigurationProperties(prefix = "tomcat.https")
    @Data
    public static class TomcatSslConnectorProperties {
        private boolean enable;
        private int port;
        private String keystore;
        private String keystorePassword;
        private String keystoreType;
        private String keyAlias;
        private boolean secure;
        private int timeout;
        private String scheme;
        private boolean allowTrace;
        private boolean httpOnly;
    }

}
