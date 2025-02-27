package com.sdcconsulting.sessions.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    public static final String CACHE_SISA_IDS = "sisaIds";

    @Override
    public void customize(final ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(List.of(
                CACHE_SISA_IDS
        ));
    }

}
