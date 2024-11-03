package com.vk.logbot.bot.util

import com.vk.logbot.bot.config.TelegramConfig
import com.vk.logbot.bot.exception.BotFileNotFoundException
import com.vk.logbot.bot.service.LogBot
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import org.telegram.telegrambots.meta.api.objects.ApiResponse
import org.telegram.telegrambots.meta.api.objects.File

@Component
class FileDownloader(
    private val restTemplate: RestTemplate,
    private val telegramConfig: TelegramConfig,
    private val logBot: LogBot
) {

    fun getFile(fileId: String, chatId: Long): java.io.File {
        return logBot.downloadFile(getFilePath(fileId, chatId))
    }

    private fun getFilePath(fileId: String, chatId: Long): String {
        val url = "${telegramConfig.apiUrl}/bot${telegramConfig.botToken}/getFile"
        val uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("file_id", fileId).encode().build().toUri()

        val responseBody = restTemplate.exchange(uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<ApiResponse<File>>() {}).body
        val file = responseBody?.result ?: throw BotFileNotFoundException(fileId, chatId)

        return file.filePath
    }
}