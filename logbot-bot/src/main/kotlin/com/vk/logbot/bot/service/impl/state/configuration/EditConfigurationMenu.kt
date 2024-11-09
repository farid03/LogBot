package com.vk.logbot.bot.service.impl.state.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.CacheKey
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.CacheDataType
import com.vk.logbot.bot.model.enm.CallbackType
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.ChatInfoService
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.temp.ConfigDao
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.ConfigUtils
import com.vk.logbot.bot.util.KeyboardCreator
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

/**
 * Меню редактирования конфигураций.
 */
@BotState(StateNames.EDIT_CONFIGURATION_MENU)
class EditConfigurationMenu(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator,
    private val chatInfoService: ChatInfoService,
    private val configDao: ConfigDao,
    private val configUtils: ConfigUtils,
    private val callbackUtils: CallbackUtils,
    private val cache: Cache<CacheKey, Any>
) : State(
    stateContext, botApiMethodExecutor, keyboardCreator, linkedMapOf(
        Command.RENAME to null,
        Command.EDIT_REG_EXP to null,
        Command.BACK to StateNames.CONFIGURATIONS_MENU,
        Command.MAIN_MENU to StateNames.MAIN_MENU
    )
) {
    override fun initState(chatId: Long) {
        val userId = chatInfoService.getUserIdByChatId(chatId)!!
        if (configDao.getConfigsByUserId(userId).isEmpty()) {
            botApiMethodExecutor.executeBotApiMethod(
                SendMessage(
                    chatId.toString(), "У вас нет конфигураций. Вы возвращены в меню конфигураций"
                )
            )
            stateContext.switchState(chatId, StateNames.CONFIGURATIONS_MENU)
            return
        }
        super.initState(chatId)
    }

    override fun handleCommandMessage(chatId: Long, command: Command) {
        when (command) {
            Command.RENAME -> sendConfigListMessage(chatId, CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_RENAME)
            Command.EDIT_REG_EXP -> sendConfigListMessage(
                chatId, CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_EDIT_REG_EXP
            )

            else -> super.handleCommandMessage(chatId, command)
        }
    }

    override fun handleCallbackQuery(chatId: Long, query: CallbackQuery) {
        val allowedCallbackTypes = setOf(
            CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_RENAME,
            CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_EDIT_REG_EXP
        )
        val callbackData = callbackUtils.parseCallbackData(query.data)
        if (!allowedCallbackTypes.contains(callbackData.callbackType)) {
            super.handleCallbackQuery(chatId, query)
        }

        val configId = callbackData.data.toInt()
        cache.put(CacheKey(chatId, CacheDataType.EDITABLE_CONFIGURATION_ID), configId)

        val newStateName = when (callbackData.callbackType) {
            CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_RENAME -> StateNames.EDIT_CONFIGURATION_AWAITING_NAME
            CallbackType.EDIT_CONFIGURATION_MENU_CHOICE_FOR_EDIT_REG_EXP -> StateNames.EDIT_CONFIGURATION_AWAITING_REG_EXP
            else -> throw RuntimeException()
        }
        stateContext.switchState(chatId, newStateName)
    }

    /**
     * Отправляет сообщение со списком конфигов.
     */
    private fun sendConfigListMessage(chatId: Long, callbackType: CallbackType) {
        val userId = chatInfoService.getUserIdByChatId(chatId)!!
        val configs = configDao.getConfigsByUserId(userId)
        val configListMessage = configUtils.createConfigListMessage(configs, chatId, callbackType)
        botApiMethodExecutor.executeBotApiMethod(configListMessage)
    }
}