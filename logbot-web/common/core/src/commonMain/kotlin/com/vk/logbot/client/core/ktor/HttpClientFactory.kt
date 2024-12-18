package com.vk.logbot.client.core.ktor

import com.vk.logbot.client.core.net.ApiException
import com.vk.logbot.client.core.net.ApiIOException
import com.vk.logbot.client.core.net.InvalidAuthTokenException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.*
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.json.Json

class HttpClientFactory {
    private val json: Json = Json.Default
    val errorFlow = MutableSharedFlow<InvalidAuthTokenException>(replay = 0)
    private val rest: HttpClient by lazy {
        HttpClient(HttpEngineFactory().createEngine()) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    json = json,
                    contentType = ContentType.Any,
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 45000
                socketTimeoutMillis = 45000
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            defaultRequest {
                host = BuildKonfig.SERVER_PATH
                port = BuildKonfig.SERVER_PORT.toInt()
                url {
                    protocol = URLProtocol.HTTP
                }
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                contentType(ContentType.Application.Json)
            }
            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    when (exception) {
                        is ClientRequestException -> {
                            if (exception.response.status == HttpStatusCode.Unauthorized) {
                                errorFlow.emit(InvalidAuthTokenException(exception.response.status.toString()))
                            }
                            throw ApiException(exception.response.status.toString())
                        }

                        is JsonConvertException -> exception.printStackTrace()
                        is IOException -> throw ApiIOException(exception.message!!)
                        else -> throw exception
                    }
                }
            }
        }
    }

    fun rest(): HttpClient {
        return rest
    }
}
