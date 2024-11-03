package com.vk.logbot.bot.model

import com.vk.logbot.bot.enm.CallbackDataType

data class CallbackData(val callbackDataType: CallbackDataType, val userId: Long, val otherData: String)
