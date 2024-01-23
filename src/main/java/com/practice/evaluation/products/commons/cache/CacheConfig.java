package com.practice.evaluation.products.commons.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @apiNote CacheConfig, Encargado de configurar el cache
 *
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport{

    /**
     *
     * @return {@link ConcurrentMapCacheManager}
     */
    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("EvaluationCache");
        cacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES));
        return cacheManager;
    }
}