package com.vk.logbot.webjmix.util

import io.jmix.core.session.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("session")
class TelegramIdProvider {

    @Autowired
    private lateinit var sessionData: SessionData

    fun getTelegramId(): Long? {
        return sessionData.getAttribute("telegramId") as Long?
    }

    fun setTelegramId(telegramId: Long) {
        sessionData.setAttribute("telegramId", telegramId)
    }
}