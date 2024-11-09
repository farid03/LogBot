package com.vk.logbot.bot.service

import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.util.KeyboardCreator
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup

/**
 * Состояние бота.
 */
abstract class State(

    /**
     * Контекст состояний. Необходим для переключения состояний.
     */
    protected val stateContext: StateContext,

    /**
     * Исполнитель методов Telegram Bot API.
     */
    protected val botApiMethodExecutor: BotApiMethodExecutor,
    /**
     * Конструктор клавиатур.
     */
    protected val keyboardCreator: KeyboardCreator,
    /**
     * Связь между командами и состояниями, в которые необходимо перейти по команде.
     */
    protected val commandStateNameMap: Map<Command, String?>?
) {
    /**
     * Постоянная клавиатура.
     */
    protected val replyKeyboardMarkup by lazy {
        createReplyKeyboardMarkup()
    }

    /**
     * Вызывается при входе в состояние и инициализирует его.
     *
     * @param chatId идентификатор чата
     */
    open fun initState(chatId: Long) {
        val answer = SendMessage(chatId.toString(), "Выберите действие")
        answer.replyMarkup = replyKeyboardMarkup
        botApiMethodExecutor.executeBotApiMethod(answer)
    }

    /**
     * Обрабатывает сообщение.
     */
    fun handleMessage(chatId: Long, message: Message) {
        val command = Command.getByText(message.text)
        if (command != null) {
            handleCommandMessage(chatId, command)
        } else {
            handleNotCommandMessage(chatId, message)
        }
    }

    /**
     * Обрабатывает сообщенеи как команду.
     */
    protected open fun handleCommandMessage(chatId: Long, command: Command) {
        if (Command.START == command) {
            stateContext.switchToStartState(chatId)
            return
        }
        if (!commandStateNameMap.isNullOrEmpty() && commandStateNameMap.containsKey(command)) {
            stateContext.switchState(chatId, commandStateNameMap[command]!!)
            return
        }
    }

    /**
     * Обрабатывает сообщение как текст.
     */
    protected open fun handleNotCommandMessage(chatId: Long, message: Message) {
        botApiMethodExecutor.executeBotApiMethod(SendMessage(chatId.toString(), "Неизвестная команда"))
    }

    /**
     * Обрабатывает коллбэк.
     */
    open fun handleCallbackQuery(chatId: Long, query: CallbackQuery) {
        botApiMethodExecutor.executeBotApiMethod(SendMessage(chatId.toString(), "Неизвестная команда"))
    }

    /**
     * Создаёт постоянную клавиатуру.
     */
    protected open fun createReplyKeyboardMarkup(): ReplyKeyboardMarkup? {
        val commands = commandStateNameMap?.keys?.map { it.text }?.toList()
        if (commands.isNullOrEmpty()) {
            return null
        }
        return keyboardCreator.createReplyKeyboardMarkup(commands)
    }
}