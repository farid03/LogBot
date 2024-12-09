package com.vk.logbot.web.telegram

import com.vk.logbot.web.model.UserInfo

external class WebAppUser {

    @JsName("id")
    val id: Int
}

fun WebAppUser.toUser() = UserInfo(
    telegramId = this.id,
    idCompany = ""
)