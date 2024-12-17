package com.vk.logbot.bot.service.impl

import com.vk.logbot.bot.LogBot
import com.vk.logbot.bot.service.BotApiMethodExecutor
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import java.io.Serializable

@Service
class BotApiMethodExecutorImpl(private val logBot: LogBot) : BotApiMethodExecutor {

    override fun <T : Serializable, Method : BotApiMethod<T>> executeBotApiMethod(method: Method) {
        logBot.execute(method)
    }
}