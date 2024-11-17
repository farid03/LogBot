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
import com.vk.logbot.bot.util.FileDownloader
import com.vk.logbot.bot.util.KeyboardCreator
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

/**
 * Создание конфигурации (ожидание ввода регулярного выражения).
 */
@BotState(StateNames.CREATE_CONFIGURATION_AWAITING_REG_EXP)
class CreateConfigurationAwaitingRegExpState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val cache: Cache<CacheKey, Any>,
    private val configDao: ConfigDao,
    private val fileDownloader: FileDownloader
) : State(
    stateContext,
    botApiMethodExecutor,
    keyboardCreator,
    linkedMapOf(
        Command.BACK to StateNames.CREATE_CONFIGURATION_AWAITING_NAME,
        Command.MAIN_MENU to StateNames.MAIN_MENU
    )
) {
    override fun initState(chatId: Long) {
        val answer = SendMessage(chatId.toString(), "Введите регулярное выражение или загрузите файл с ним")
        answer.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(answer)
    }

    override fun handleNotCommandMessage(chatId: Long, message: Message) {
        if (!message.hasText() && !message.hasDocument()) {
            initState(chatId)
            return
        }

        val cachedName =
            cache.getIfPresent(CacheKey(chatId, CacheDataType.CREATABLE_CONFIGURATION_NAME)) as String? ?: let {
                botApiMethodExecutor.executeBotApiMethod(
                    SendMessage(
                        chatId.toString(),
                        "Вы длительное время не пользовались ботом, поэтому название создаваемой конфигурации сбросилось. Начните создание конфигурации сначала"
                    )
                )
                stateContext.switchState(chatId, StateNames.CREATE_CONFIGURATION_AWAITING_NAME)
                return
            }
        val userId = message.from.id
        val regExp = if (message.hasDocument()) {
            val file = fileDownloader.getFile(message.document.fileId)
            file.readText()
        } else {
            message.text
        }

        val config = Config(null, userId, cachedName, regExp)
        configDao.saveConfig(config)

        botApiMethodExecutor.executeBotApiMethod(
            SendMessage(
                chatId.toString(), "Создана новая конфигурация:\n" +
                        "Название: \"${config.name}\"\n" +
                        "Регулярное выражение: \"${config.regExp}\"\n" +
                        "Вы возвращены в меню конфигураций"
            )
        )
        stateContext.switchState(chatId, StateNames.CONFIGURATIONS_MENU)
    }
}