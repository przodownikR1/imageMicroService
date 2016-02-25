package pl.java.scalatech.config;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import com.google.common.cache.Cache;
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache<Object, Object> oneMinuteCache = newBuilder().expireAfterWrite(1, MINUTES).build();
        cacheManager.setCaches(Arrays.asList(new GuavaCache("entityOne", oneMinuteCache)));
        return cacheManager;
    }
}
