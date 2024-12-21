package com.vk.logbot.webjmix.service.impl

import com.vk.logbot.webjmix.service.SessionDataProvider
import io.jmix.core.session.SessionData
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import org.springframework.web.context.WebApplicationContext

@Service
@Scope(WebApplicationContext.SCOPE_SESSION)
class SessionDataProviderImpl(private val sessionData: SessionData) : SessionDataProvider {

    companion object {
        private const val TELEGRAM_ID = "telegramId"
        private const val CURRENT_CONFIG_ID = "currentConfigId"
    }

    override fun getCurrentTelegramId(): Long? {
        return sessionData.getAttribute(TELEGRAM_ID) as Long?
    }

    override fun setCurrentTelegramId(telegramId: Long) {
        sessionData.setAttribute(TELEGRAM_ID, telegramId)
    }

    override fun getCurrentConfigId(): Long? {
        return sessionData.getAttribute(CURRENT_CONFIG_ID) as Long?
    }

    override fun setCurrentConfigId(configId: Long) {
        sessionData.setAttribute(CURRENT_CONFIG_ID, configId)
    }
}