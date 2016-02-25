package pl.java.scalatech;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KeySpringTest.MyKey.class)
@Slf4j
public class KeySpringTest {

    @Autowired
    private KeyGenerator keyGenerator;

    @Test
    @SneakyThrows
    public void test() {
        log.info("method {}", this.getClass().getMethod("test"));
        log.info("{}", keyGenerator.generate(this, this.getClass().getMethod("test"), "223"));
    }

    @Configuration
    static class MyKey {
        @Bean
        public KeyGenerator keyGenerator() {
            return new SimpleKeyGenerator();
        }
    }
}
/*
 * @Bean
 * @Override
 * public CacheManager cacheManager() {
 * SimpleCacheManager cacheManager = new SimpleCacheManager();
 * cacheManager.setCacheSpecification("maximumSize=10000,expireAfterWrite=3d");
 * cacheManager.setCacheNames(Arrays.asList(
 * "entry",
 * "recentPost",
 * "accessTokenAuthentication",
 * "configs"));
 * return cacheManager;
 * }
 */