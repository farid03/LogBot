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
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Создание новой конфигурации (ожидание ввода названия конфигурации).
 */
@BotState(StateNames.CREATE_CONFIGURATION_AWAITING_NAME)
class CreateConfigurationAwaitingNameState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val cache: Cache<CacheKey, Any>
) : State(
    stateContext, botApiMethodExecutor, keyboardCreator, linkedMapOf(
        Command.BACK to StateNames.CONFIGURATIONS_MENU,
        Command.MAIN_MENU to StateNames.MAIN_MENU
    )
) {
    override fun initState(chatId: Long) {
        cache.invalidate(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_NAME))

        val answer = SendMessage(chatId.toString(), "Введите название конфигурации")
        answer.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(answer)
    }

    override fun handleNotCommandMessage(chatId: Long, message: Message) {
        if (!message.hasText()) {
            initState(chatId)
            return
        }

        cache.put(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_NAME), message.text)
        stateContext.switchState(chatId, StateNames.CREATE_CONFIGURATION_AWAITING_REG_EXP)
    }
}