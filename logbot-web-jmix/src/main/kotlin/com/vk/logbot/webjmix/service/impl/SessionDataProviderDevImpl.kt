package com.vk.logbot.webjmix.service.impl

import io.jmix.core.session.SessionData
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import org.springframework.web.context.WebApplicationContext

@Service
@Profile("dev")
@Scope(WebApplicationContext.SCOPE_SESSION)
class SessionDataProviderDevImpl(
    sessionData: SessionData
) : SessionDataProviderImpl(sessionData) {

    @Value("\${dev-telegram-id}")
    var devTelegramId: Long? = null

    override fun getCurrentTelegramId(): Long? {
        if (devTelegramId == null) {
            return super.getCurrentTelegramId()
        }
        return devTelegramId
    }
}