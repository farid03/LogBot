package com.vk.logbot.bot.service.state

import com.vk.logbot.bot.enm.CallbackDataType
import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.service.LogBot
import com.vk.logbot.bot.service.external.ConfigFileService
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.InlineKeyboardMaker
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class RemoveConfigFileState(
    previewState: State?,
    nextStates: MutableMap<Command, State>,
    keyboard: ReplyKeyboardMarkup,
    private val configFileService: ConfigFileService,
    private val logBot: LogBot,
    private val inlineKeyboardMaker: InlineKeyboardMaker,
    private val callbackUtils: CallbackUtils
) : State(previewState, nextStates, keyboard) {

    override fun handleMessage(message: Message): BotApiMethod<*> {
        val initialMessage = createSendMessage(
            message,
            "Выберите конфигурацию, которую хотите удалить (или нажмите кнопку \"Удалить все\" для удаления всех конфигураций"
        )
        logBot.execute(initialMessage)
        val userId = message.from.id
        logBot.execute(
            createSendMessage(
                message,
                "Текущие конфигурации:",
                inlineKeyboardMaker.createKeyboard(
                    configFileService.getConfigFiles(userId).map { it.name },
                    CallbackDataType.REMOVE_CONFIG,
                    userId
                )
            )
        )
        return createSendMessage(message, "")
    }

    override fun terminalHandleMessage(message: Message): BotApiMethod<*> {
        if (Command.REMOVE_ALL == Command.getByText(message.text)) {
            configFileService.removeConfigFiles(message.from.id)
            return createSendMessage(message, "Все конфигурации удалены")
        }
        return super.terminalHandleMessage(message)
    }

    override fun handleCallbackQuery(
        callbackQuery: CallbackQuery
    ): BotApiMethod<*> {
        val callbackData = callbackUtils.parseCallbackData(callbackQuery.data)
        configFileService.removeConfigFile(callbackData.userId, callbackData.otherData, callbackQuery.message.chatId)
        return createSendMessage(callbackQuery, "Конфигурация \"${callbackData.otherData}\" удалена")
    }
}