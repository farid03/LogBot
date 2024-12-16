package com.vk.logbot.bot.util

import com.vk.logbot.bot.config.TelegramConfig
import com.vk.logbot.bot.exception.BotFileNotFoundException
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import org.telegram.telegrambots.facilities.filedownloader.TelegramFileDownloader
import org.telegram.telegrambots.meta.api.objects.ApiResponse
import org.telegram.telegrambots.meta.api.objects.File

/**
 * Загрузчик файлов из Telegram.
 */
@Component
class FileDownloader(
    private val restTemplate: RestTemplate,
    private val telegramConfig: TelegramConfig,
    private val telegramFileDownloader: TelegramFileDownloader
) {

    /**
     * Загружает файл из Telegram по его id.
     */
    fun getFile(fileId: String): java.io.File {
        return telegramFileDownloader.downloadFile(getFilePath(fileId))
    }

    /**
     * Возвращает filePath файла в Telegram (для загрузки файла недостаточно одного fileId).
     */
    private fun getFilePath(fileId: String): String {
        val url = "${telegramConfig.apiUrl}/bot${telegramConfig.token}/getFile"
        val uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("file_id", fileId).encode().build().toUri()

        val responseBody = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<ApiResponse<File>>() {}).body
        val file = responseBody?.result ?: throw BotFileNotFoundException(fileId)

        return file.filePath
    }
}