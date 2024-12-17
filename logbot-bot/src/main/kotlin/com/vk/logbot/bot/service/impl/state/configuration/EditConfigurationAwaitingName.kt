package com.vk.logbot.bot.service.impl.state.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.CacheKey
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.CacheDataType
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.util.KeyboardCreator
import com.vk.logbot.serverrestclient.ServerClient
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Редактирование конфигурации (ожидание ввода названия).
 */
@BotState(StateNames.EDIT_CONFIGURATION_AWAITING_NAME)
class EditConfigurationAwaitingName(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val cache: Cache<CacheKey, Any>,
    private val serverClient: ServerClient
) : State(
    stateContext,
    botApiMethodExecutor,
    keyboardCreator,
    linkedMapOf(
        Command.BACK to StateNames.EDIT_CONFIGURATION_MENU,
        Command.MAIN_MENU to StateNames.MAIN_MENU
    )
) {
    override fun initState(chatId: Long) {
        val answer = SendMessage(chatId.toString(), "Введите новое название")
        answer.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(answer)
    }

    override fun handleNotCommandMessage(chatId: Long, message: Message) {
        if (!message.hasText()) {
            initState(chatId)
            return
        }

        val cacheKey = CacheKey(chatId, CacheDataType.EDITABLE_CONFIGURATION_ID)
        val configId = cache.getIfPresent(cacheKey) as Long? ?: let {
            botApiMethodExecutor.executeBotApiMethod(
                SendMessage(
                    chatId.toString(),
                    "Вы длительное время не пользовались ботом. Произведён возврат в меню редактирования конфигураций"
                )
            )
            stateContext.switchState(chatId, StateNames.EDIT_CONFIGURATION_MENU)
            return
        }

        val config = serverClient.getConfigById(configId)
        serverClient.editConfig(configId, message.text, config.regExp, config.message, config.active)

        cache.invalidate(cacheKey)
        botApiMethodExecutor.executeBotApiMethod(
            SendMessage(
                chatId.toString(),
                "Конфигурация переименована! Произведён возврат в меню редактирования конфигураций"
            )
        )
        stateContext.switchState(chatId, StateNames.EDIT_CONFIGURATION_MENU)
    }
}