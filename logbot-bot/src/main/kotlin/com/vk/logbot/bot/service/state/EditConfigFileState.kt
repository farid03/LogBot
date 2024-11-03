package com.vk.logbot.bot.service.state

import com.vk.logbot.bot.enm.CallbackDataType
import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.enm.InputData
import com.vk.logbot.bot.exception.BotConfigFileNotValidException
import com.vk.logbot.bot.exception.BotConfigNotFoundException
import com.vk.logbot.bot.exception.BotIllegalCommandException
import com.vk.logbot.bot.service.LogBot
import com.vk.logbot.bot.service.cache.InputDataCache
import com.vk.logbot.bot.service.external.ConfigFileService
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.FileDownloader
import com.vk.logbot.bot.util.InlineKeyboardMaker
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class EditConfigFileState(
    previewState: State?,
    nextStates: MutableMap<Command, State>,
    keyboard: ReplyKeyboardMarkup,
    private val editKeyboard: ReplyKeyboardMarkup,
    private val fileDownloader: FileDownloader,
    private val configFileService: ConfigFileService,
    private val inputDataCache: InputDataCache,
    private val logBot: LogBot,
    private val inlineKeyboardMaker: InlineKeyboardMaker,
    private val callbackUtils: CallbackUtils
) : State(previewState, nextStates, keyboard) {

    override fun handleMessage(message: Message): BotApiMethod<*> {
        val initialMessage = createSendMessage(
            message,
            "Выберите конфигурацию, которую хотите изменить"
        )
        logBot.execute(initialMessage)
        val userId = message.from.id
        logBot.execute(
            createSendMessage(
                message, "Текущие конфигурации:", inlineKeyboardMaker.createKeyboard(
                    configFileService.getConfigFiles(userId).map { it.name },
                    CallbackDataType.EDIT_CONFIG,
                    userId
                )
            )
        )
        return createSendMessage(message, "")
    }

    override fun terminalHandleMessage(message: Message): BotApiMethod<*> {
        if (message.hasDocument()) {
            return replaceConfigFile(message)
        }

        val chatId = message.chatId
        val configFileName = inputDataCache.getInputDataValue(chatId, InputData.EDITING_CONFIG_FILE_NAME)
            ?: return createSendMessage(message, "Выберите редактируемый файл")
        val command = Command.getByText(message.text)

        if (command == Command.RENAME || command == Command.EDIT_REG_EXP) {
            return saveEditCommand(command, message)
        }
        val cachedCommand =
            Command.getByText(inputDataCache.getInputDataValue(chatId, InputData.EDITING_CONFIG_LAST_COMMAND) ?: "")
        if (cachedCommand == null) {
            return createSendMessage(message, "Выберите действие", editKeyboard)
        }

        when (cachedCommand) {
            Command.RENAME -> renameConfigFile(message, configFileName)
            Command.EDIT_REG_EXP -> editRegExpInConfigFile(message, configFileName)
            else -> throw BotIllegalCommandException(cachedCommand.text, chatId)
        }

        clearInputData(chatId)
        return createSendMessage(
            message, "Файл успешно изменён!"
        )
    }

    private fun renameConfigFile(message: Message, configFileName: String) {
        val userId = message.from.id
        val chatId = message.chat.id
        val newConfigName = message.text
        val configFile = configFileService.getConfigFile(userId, configFileName)
        configFileService.removeConfigFile(userId, configFileName, chatId)
        if (configFile != null) {
            configFileService.createConfigFile(newConfigName, configFile.regExp, userId)
        }
    }

    private fun editRegExpInConfigFile(message: Message, configFileName: String) {
        val userId = message.from.id
        val chatId = message.chat.id
        val newConfigRegexp = message.text
        val configFile = configFileService.getConfigFile(userId, configFileName)
        configFileService.removeConfigFile(userId, configFileName, chatId)
        if (configFile != null) {
            configFileService.createConfigFile(configFile.name, newConfigRegexp, userId)
        }
    }

    private fun saveEditCommand(command: Command, message: Message): BotApiMethod<*> {
        val chatId = message.chatId
        inputDataCache.putInputDataValue(chatId, InputData.EDITING_CONFIG_LAST_COMMAND, command.text)
        return createSendMessage(
            message, "Введите новое ${
                when (command) {
                    Command.RENAME -> "название"
                    Command.EDIT_REG_EXP -> "регулярное выражение"
                    else -> throw BotIllegalCommandException(command.text, chatId)
                }
            }", editKeyboard
        )
    }

    private fun replaceConfigFile(message: Message): BotApiMethod<*> {
        val chatId = message.chatId
        try {
            val file = fileDownloader.getFile(message.document.fileId, chatId)
            configFileService.uploadConfigFile(file, message.from.id, chatId)
            return createSendMessage(message, "Файл успешно загружен!")
        } catch (e: BotConfigFileNotValidException) {
            clearInputData(message.chatId)
            return createSendMessage(
                message,
                e.publicMessage + "\nДля создания новой конфигурации отправьте файл в чат или введите название новой конфигурации"
            )
        }
    }

    override fun handleCallbackQuery(callbackQuery: CallbackQuery): BotApiMethod<*> {
        val callbackData = callbackUtils.parseCallbackData(callbackQuery.data)
        val configName = callbackData.otherData
        val chatId = callbackQuery.message.chatId

        if (configFileService.getConfigFiles(callbackData.userId)
                .none { it.userId == callbackData.userId && it.name == configName }
        ) {
            throw BotConfigNotFoundException(configName, chatId)
        }
        inputDataCache.putInputDataValue(chatId, InputData.EDITING_CONFIG_FILE_NAME, configName)
        return createSendMessage(
            callbackQuery,
            "Конфигурация $configName выбрана\nВыберите действие или загрузите новый файл",
            editKeyboard
        )
    }

    override fun clearInputData(chatId: Long) {
        inputDataCache.removeInputDataValue(chatId, InputData.EDITING_CONFIG_FILE_NAME)
        inputDataCache.removeInputDataValue(chatId, InputData.EDITING_CONFIG_LAST_COMMAND)
    }
}