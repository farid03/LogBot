package com.vk.logbot.client.core.net

open class ApiException(message: String) : IllegalStateException(message)

class ApiIOException(message: String) : ApiException(message)

class InvalidAuthTokenException(message: String) : ApiException(message)