package com.vk.logbot.bot.service.command

import com.vk.logbot.bot.annotation.BotCommand
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message

@BotCommand
class StartCommand : Command {

    override fun execute(message: Message): BotApiMethod<*> {
        val chatId = getChatId(message)
        return SendMessage(chatId, "Добро пожаловать! Отправьте конфигурационный файл")
    }
}