package com.vk.logbot.client.core.ktor

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.js.JsClient

actual class HttpEngineFactory {
 actual fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = JsClient()
}