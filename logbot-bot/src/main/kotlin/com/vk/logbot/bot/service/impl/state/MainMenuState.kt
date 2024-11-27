package com.vk.logbot.bot.service.impl.state

import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.util.KeyboardCreator

/**
 * Главное меню.
 */
@BotState(StateNames.MAIN_MENU)
class MainMenuState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator
) : State(
    stateContext,
    botApiMethodExecutor,
    keyboardCreator,
    mapOf(Command.CONFIGURATIONS to StateNames.CONFIGURATIONS_MENU)
)