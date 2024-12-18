package com.vk.logbot.client.core.net

import kotlinx.serialization.Serializable

@Serializable
data class ApiErrorResponse(
    override val success: Boolean,
    override val message: String?,
    val errorCode: String?
) : BaseResponse {
    companion object{
        const val InvalidTokenCode = "no_user"
    }
}