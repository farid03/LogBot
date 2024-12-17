package com.vk.logbot.bot.service.impl.state.configuration

import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.CallbackType
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.temp.ConfigDao
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.ConfigUtils
import com.vk.logbot.bot.util.KeyboardCreator
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

/**
 * Меню удаления конфигурации.
 */
@BotState(StateNames.REMOVE_CONFIGURATION_MENU)
class RemoveConfigurationMenuState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val chatInfoService: ChatInfoService,
    private val configDao: ConfigDao,
    private val configUtils: ConfigUtils,
    private val callbackUtils: CallbackUtils
) : State(
    stateContext, botApiMethodExecutor, keyboardCreator, linkedMapOf(
        Command.REMOVE_ALL to null,
        Command.BACK to StateNames.CONFIGURATIONS_MENU
    )
) {
    override fun initState(chatId: Long) {
        val userId = chatInfoService.getUserIdByChatId(chatId)!!
        if (configDao.getConfigsByUserId(userId).isEmpty()) {
            botApiMethodExecutor.executeBotApiMethod(
                SendMessage(
                    chatId.toString(),
                    "У вас нет конфигураций. Вы возвращены в меню конфигураций"
                )
            )
            stateContext.switchState(chatId, StateNames.CONFIGURATIONS_MENU)
            return
        }

        val initMessage = SendMessage(chatId.toString(), "Выберите удаляемую конфигурацию или нажмите \"Удалить все\"")
        initMessage.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(initMessage)

        val configs = configDao.getConfigsByUserId(userId)
        val configListMessage =
            configUtils.createConfigListMessage(configs, chatId, CallbackType.REMOVE_CONFIGURATION_MENU_CHOICE)
        botApiMethodExecutor.executeBotApiMethod(configListMessage)
    }

    override fun handleCommandMessage(chatId: Long, command: Command) {
        when (command) {
            Command.REMOVE_ALL -> {
                val userId = chatInfoService.getUserIdByChatId(chatId)!!
                configDao.removeConfigsByUserId(userId)
                botApiMethodExecutor.executeBotApiMethod(
                    SendMessage(
                        chatId.toString(),
                        "Все конфигурации удалены. Вы возвращены в меню конфигураций"
                    )
                )
                stateContext.switchState(chatId, StateNames.CONFIGURATIONS_MENU)
            }

            else -> super.handleCommandMessage(chatId, command)
        }
    }

    override fun handleCallbackQuery(chatId: Long, query: CallbackQuery) {
        val callbackData = callbackUtils.parseCallbackData(query.data)
        if (CallbackType.REMOVE_CONFIGURATION_MENU_CHOICE != callbackData.callbackType) {
            super.handleCallbackQuery(chatId, query)
        }

        val configId = callbackData.data.toInt()
        configDao.removeConfigById(configId)
        botApiMethodExecutor.executeBotApiMethod(SendMessage(chatId.toString(), "Конфигурация успешно удалена!"))
        initState(chatId)
    }
}