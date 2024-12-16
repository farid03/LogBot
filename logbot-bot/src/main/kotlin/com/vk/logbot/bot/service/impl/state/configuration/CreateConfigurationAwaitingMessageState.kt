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
import com.vk.logbot.bot.temp.Config
import com.vk.logbot.bot.temp.ConfigDao
import com.vk.logbot.bot.util.KeyboardCreator
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Создание конфигурации (ожидание ввода сообщения уведомления о логе).
 */
@BotState(StateNames.CREATE_CONFIGURATION_AWAITING_MESSAGE)
class CreateConfigurationAwaitingMessageState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val cache: Cache<CacheKey, Any>,
    private val configDao: ConfigDao
) : State(
    stateContext, botApiMethodExecutor, keyboardCreator, linkedMapOf(
        Command.BACK to StateNames.CONFIGURATIONS_MENU,
        Command.MAIN_MENU to StateNames.MAIN_MENU
    )
) {
    override fun initState(chatId: Long) {
        val answer = SendMessage(
            chatId.toString(),
            "Введите сообщение, которое будет показываться при нахождении заданных логов"
        )
        answer.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(answer)
    }

    override fun handleNotCommandMessage(chatId: Long, message: Message) {
        if (!message.hasText() && !message.hasDocument()) {
            initState(chatId)
            return
        }

        val cachedName = cache.getIfPresent(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_NAME)) as String?
        val cachedRegExp =
            cache.getIfPresent(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_REG_EXP)) as String?

        if (cachedName == null || cachedRegExp == null) {
            botApiMethodExecutor.executeBotApiMethod(
                SendMessage(
                    chatId.toString(),
                    "Вы длительное время не пользовались ботом, поэтому данные создаваемой конфигурации сбросилось. Начните создание конфигурации сначала"
                )
            )
            stateContext.switchState(chatId, StateNames.CREATE_CONFIGURATION_AWAITING_NAME)
            return
        }

        val userId = message.from.id
        val config = Config(null, userId, cachedName, cachedRegExp, message.text, false)
        configDao.saveConfig(config)
        invalidateCaches(chatId)

        botApiMethodExecutor.executeBotApiMethod(
            SendMessage(
                chatId.toString(), "Создана новая конфигурация:\n" +
                        "Название: \"${config.name}\"\n" +
                        "Регулярное выражение: \"${config.regExp}\"\n" +
                        "Сообщение: \"${config.message}\"\n" +
                        "Вы возвращены в меню конфигураций"
            )
        )
        stateContext.switchState(chatId, StateNames.CONFIGURATIONS_MENU)
    }

    private fun invalidateCaches(chatId: Long) {
        cache.invalidate(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_NAME))
        cache.invalidate(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_REG_EXP))
    }
}