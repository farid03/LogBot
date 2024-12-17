package com.vk.logbot.bot.util

import com.vk.logbot.bot.model.CallbackData
import com.vk.logbot.bot.model.enm.CallbackType
import org.springframework.stereotype.Component

/**
 * Util-класс для работы с коллбэками.
 */
@Component
class CallbackUtils {

    /**
     * Создаёт строку вида "тип коллбэка <пробел> данные коллбэка".
     *
     * Тип коллбэка хранится как число, потому что поле data в коллбэке ограничено 64 символами и требуется их экономия.
     */
    fun createCallbackData(callbackType: CallbackType, data: String): String {
        return "${callbackType.ordinal} $data"
    }

    /**
     * Переводит строковое представление data в коллбэке в [CallbackData]
     */
    fun parseCallbackData(dataFromCallbackQuery: String): CallbackData {
        val callbackType = CallbackType.getByOrdinal(dataFromCallbackQuery.split(" ")[0].toInt())
        return CallbackData(callbackType, dataFromCallbackQuery.split(" ")[1])
    }
}