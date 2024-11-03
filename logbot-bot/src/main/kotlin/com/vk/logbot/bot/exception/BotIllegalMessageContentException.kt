package com.vk.logbot.bot.exception

class BotIllegalMessageContentException(chatId: Long) : BotException(
    "Разрешены к отправке только сообщения с текстом и файлами",
    "Разрешены к отправке только сообщения с текстом и файлами",
    chatId
)