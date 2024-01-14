package com.practice.evaluation.products.commons.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @apiNote CacheConfig, Encargado de configurar el cache
 *
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     *
     * @return {@link ConcurrentMapCacheManager}
     */
    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager("EvaluationCache");
    }
}