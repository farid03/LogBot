package com.vk.logbot.bot.controller

import com.vk.logbot.bot.service.StateContext
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

/**
 * REST-контроллер для получения обновлений в чатах.
 */
@RestController
class WebhookController(
    private val stateContext: StateContext
) {

    /**
     * Получает обновление чата от Telegram.
     */
    @PostMapping("/callback/update/\${bot.token}")
    fun onWebhookUpdateReceived(@RequestBody update: Update): BotApiMethod<*> {
        stateContext.handleUpdate(update)

        //отправляем пустышку, чтобы Telegram понял, что мы успешно обработали запрос
        //нормальные сообщения будут отправляться при обработке состояний через botApiMethodExecutor
        return SendMessage()
    }
}