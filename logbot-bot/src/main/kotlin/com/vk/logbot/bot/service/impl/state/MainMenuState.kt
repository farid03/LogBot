package com.vk.logbot.bot.service.impl.state

import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.CallbackType
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.ConfigUtils
import com.vk.logbot.bot.util.KeyboardCreator
import com.vk.logbot.serverrestclient.ServerClient
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

/**
 * Главное меню.
 */
@BotState(StateNames.MAIN_MENU)
class MainMenuState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val chatInfoService: ChatInfoService,
    private val serverClient: ServerClient,
    private val configUtils: ConfigUtils,
    private val callbackUtils: CallbackUtils
) : State(
    stateContext,
    botApiMethodExecutor,
    keyboardCreator,
    linkedMapOf(
        Command.CONFIGURATIONS to StateNames.CONFIGURATIONS_MENU,
        Command.ACTIVE_CONFIGURATIONS to null
    )
) {
    override fun handleCommandMessage(chatId: Long, command: Command) {
        when (command) {
            Command.ACTIVE_CONFIGURATIONS -> handleActiveConfigurations(chatId)
            else -> super.handleCommandMessage(chatId, command)
        }
    }

    override fun handleCallbackQuery(chatId: Long, query: CallbackQuery) {
        val callbackData = callbackUtils.parseCallbackData(query.data)
        if (callbackData.callbackType != CallbackType.MAIN_MENU_ACTIVE_CONFIGURATION_CHOICE) {
            super.handleCallbackQuery(chatId, query)
        }

        val configId = callbackData.data.toLong()
        val userId = chatInfoService.getUserIdByChatId(chatId)!!
        val configs = serverClient.getConfigsByUserId(userId)

        if (configId == -1L) {
            configs.forEach {
                serverClient.editConfig(it.id, it.name, it.regExp, it.message, false)
            }
        } else {
            val config = serverClient.getConfigById(configId)
            serverClient.editConfig(configId, config.name, config.regExp, config.message, !config.active)
        }

        val updatedConfigs = serverClient.getConfigsByUserId(userId)

        val editMessage = EditMessageReplyMarkup()
        editMessage.chatId = chatId.toString()
        editMessage.messageId = query.message.messageId
        editMessage.replyMarkup = configUtils.createConfigsKeyboardWithActiveMarks(updatedConfigs)

        botApiMethodExecutor.executeBotApiMethod(editMessage)
    }

    /**
     * Обрабатывает команду "Активные конфигурации"
     */
    private fun handleActiveConfigurations(chatId: Long) {
        val userId = chatInfoService.getUserIdByChatId(chatId)!!
        val configs = serverClient.getConfigsByUserId(userId)

        if (configs.isEmpty()) {
            botApiMethodExecutor.executeBotApiMethod(SendMessage(chatId.toString(), "У вас нет конфигураций!"))
            return
        }

        val sendMessage = SendMessage()
        sendMessage.chatId = chatId.toString()
        sendMessage.text = "Ваши конфигурации \uD83D\uDD3D"
        sendMessage.enableMarkdownV2(true)
        sendMessage.replyMarkup = configUtils.createConfigsKeyboardWithActiveMarks(configs)

        botApiMethodExecutor.executeBotApiMethod(sendMessage)
    }
}