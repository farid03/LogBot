package com.vk.logbot.bot.util

import com.vk.logbot.bot.enm.CallbackDataType
import com.vk.logbot.bot.model.CallbackData
import org.springframework.stereotype.Component

@Component
class CallbackUtils {

    fun createCallbackData(callbackDataType: CallbackDataType, userId: Long, otherData: String): String {
        return "${callbackDataType.name} $userId $otherData"
    }

    fun parseCallbackData(callbackData: String): CallbackData {
        val callbackDataTypeString = callbackData.split(" ")[0]
        val userIdString = callbackData.split(" ")[1]

        val callbackDataType = CallbackDataType.valueOf(callbackDataTypeString)
        val userId = userIdString.toLong()
        val otherData = callbackData.substring(callbackDataTypeString.length + userIdString.length + 2)

        return CallbackData(callbackDataType, userId, otherData)
    }
}