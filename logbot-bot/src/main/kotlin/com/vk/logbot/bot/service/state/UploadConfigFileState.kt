package com.vk.logbot.bot.service.state

import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.enm.InputData
import com.vk.logbot.bot.exception.BotConfigFileNotValidException
import com.vk.logbot.bot.service.cache.InputDataCache
import com.vk.logbot.bot.service.external.ConfigFileService
import com.vk.logbot.bot.util.FileDownloader
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

class UploadConfigFileState(
    previewState: State?,
    nextStates: MutableMap<Command, State>,
    keyboard: ReplyKeyboardMarkup,
    private val fileDownloader: FileDownloader,
    private val configFileService: ConfigFileService,
    private val inputDataCache: InputDataCache
) : State(previewState, nextStates, keyboard) {

    override fun handleMessage(message: Message): BotApiMethod<*> {
        clearInputData(message.chatId)
        return createSendMessage(message, "Отправьте файл конфигурации в чат или введите название конфигурации")
    }

    override fun terminalHandleMessage(message: Message): BotApiMethod<*> {
        if (message.hasDocument()) {
            return uploadConfigFile(message)
        }

        val chatId = message.chatId
        val configFileName = inputDataCache.getInputDataValue(chatId, InputData.UPLOADING_CONFIG_FILE_NAME)

        if (configFileName == null) {
            inputDataCache.putInputDataValue(chatId, InputData.UPLOADING_CONFIG_FILE_NAME, message.text)
            return createSendMessage(message, "Введите регулярное выражение для чтения логов")
        }

        val configFile = configFileService.createConfigFile(configFileName, message.text, message.from.id)
        clearInputData(chatId)
        return createSendMessage(
            message,
            "Конфигурационный файл успешно создан!" +
                    "\nНазвание: ${configFile.name}" +
                    "\nРегулярное выражение: ${configFile.regExp}" +
                    "\nДля создания новой конфигурации отправьте файл в чат или введите название новой конфигурации"
        )
    }

    private fun uploadConfigFile(message: Message): BotApiMethod<*> {
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

    override fun clearInputData(chatId: Long) {
        inputDataCache.removeInputDataValue(chatId, InputData.UPLOADING_CONFIG_FILE_NAME)
    }
}