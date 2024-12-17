package com.vk.logbot.bot.config

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.vk.logbot.bot.model.CacheKey
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

/**
 * Конфигурация кэша.
 */
@Configuration
@EnableCaching
class CacheConfig {

    /**
     * Время жизни данных в кэше.
     */
    @Value("\${cache.time-to-live}")
    private lateinit var cacheTimeToLive: Duration

    /**
     * Возвращает кэш для данных, которые необходимо передавать между состояниями.
     */
    @Bean
    fun cache(): Cache<CacheKey, Any> {
        return Caffeine.newBuilder().expireAfterWrite(cacheTimeToLive).build();
    }
}