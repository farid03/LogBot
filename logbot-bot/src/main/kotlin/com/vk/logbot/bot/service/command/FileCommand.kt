package com.vk.logbot.bot.service.command

import com.vk.logbot.bot.annotation.BotCommand
import com.vk.logbot.bot.util.FileDownloader
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

sealed interface FileCommand : Command {

	fun getFileId(message: Message): String {
		return message.document.fileId
	}

	@BotCommand
	class UploadConfigFileCommand(private val fileDownloader: FileDownloader) : FileCommand {

		private val answer = "Файл успешно загружен! Содержимое файла:\n"

		override fun execute(message: Message): BotApiMethod<*> {
			val chatId = getChatId(message)
			val fileId = getFileId(message)
			val fileContent = fileDownloader.getFile(fileId, chatId).readText()
			return SendMessage(chatId, "$answer$fileContent")
		}
	}
}
