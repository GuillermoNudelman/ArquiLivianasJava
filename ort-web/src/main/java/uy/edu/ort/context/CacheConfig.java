/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.context;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.edu.ort.util.MapTimeoutCacheManager;

@Configuration
@EnableCaching // <= importante no olvidar esta annotation
public class CacheConfig {

    private long cacheTimeout = 5;
    private String timeUnit = "MINUTES";
    private long cacheMaximunSize = 100;

    @Bean
    public CacheManager cacheManager() {
        MapTimeoutCacheManager cacheManager = new MapTimeoutCacheManager();
        cacheManager.setCacheTimeout(cacheTimeout);
        cacheManager.setTimeUnit(TimeUnit.valueOf(timeUnit));
        cacheManager.setMaximumSize(cacheMaximunSize);
        return cacheManager;
    }
}
