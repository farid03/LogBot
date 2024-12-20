package com.vk.logbot.webjmix.util

import io.jmix.core.session.SessionData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("session")
class CurrentConfigIdProvider {

    @Autowired
    private lateinit var sessionData: SessionData

    fun getCurrentConfigId(): Long? {
        return sessionData.getAttribute("currentConfigId") as Long?
    }

    fun setCurrentConfigId(configId: Long) {
        sessionData.setAttribute("currentConfigId", configId)
    }
}