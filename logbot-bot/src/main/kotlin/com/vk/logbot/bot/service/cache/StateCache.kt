package com.vk.logbot.bot.service.cache

import com.vk.logbot.bot.config.CacheConfig
import com.vk.logbot.bot.service.state.State
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service

@Service
class StateCache(cacheManager: CacheManager) :
    AbstractCache<Long, State>(cacheManager.getCache(CacheConfig.STATE_CACHE) ?: throw NullPointerException())