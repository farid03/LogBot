package com.vk.logbot.web.feature.config.item.component.contract

import com.vk.logbot.web.core.mvi.contract.MviState

data class ConfigState(
    val isCreating: Boolean = false,
    val errorMessage: String? = null,
    val nameConfig: String = "",
    val messageConfig: String = "",
    val regular: String = "",
) : MviState