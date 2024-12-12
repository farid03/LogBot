package com.vk.logbot.bot.exception

/**
 * Исключение, сигнализирующее о том, что в Telegram не нашлось файла с заданным идентификатором.
 */
class BotFileNotFoundException(fileId: String) : BotException(
    internalMessage = "Файл с id \"$fileId\" не найден",
    publicMessage = "Файл не найден"
)