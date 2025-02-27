package com.sdcconsulting.sessions.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

    public static final String CACHE_SISA_IDS = "sisaIds";

    @Override
    public void customize(final ConcurrentMapCacheManager cacheManager) {
        cacheManager.setCacheNames(List.of(
                CACHE_SISA_IDS
        ));
    }

    @CacheEvict(value = CACHE_SISA_IDS, allEntries = true)
    @Scheduled(fixedRateString = "${spring.cache.sisaIds}")
    public void emptySisaIdsCache() {
        log.info("Emptying the sisaIds Cache");
    }

}
