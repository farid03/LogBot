package com.vk.logbot.bot.service

import com.vk.logbot.bot.enm.CallbackDataType
import com.vk.logbot.bot.service.cache.StateCache
import com.vk.logbot.bot.service.state.EditConfigFileState
import com.vk.logbot.bot.service.state.RemoveConfigFileState
import com.vk.logbot.bot.util.CallbackUtils
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

@Service
class CallbackQueryHandler(
    private val stateCache: StateCache,
    private val callbackUtils: CallbackUtils,
    val editConfigFileState: EditConfigFileState,
    private val removeConfigFileState: RemoveConfigFileState
) {
    fun handle(callbackQuery: CallbackQuery): BotApiMethod<*> {
        val callbackDataType = callbackUtils.parseCallbackData(callbackQuery.data).callbackDataType

        val state = when (callbackDataType) {
            CallbackDataType.EDIT_CONFIG -> editConfigFileState
            CallbackDataType.REMOVE_CONFIG -> removeConfigFileState
        }
        stateCache.put(callbackQuery.message.chatId, state)
        return state.handleCallbackQuery(callbackQuery)
    }
}