package com.vk.logbot.bot.service.impl.state.configuration

import com.vk.logbot.bot.annotation.BotState
import com.vk.logbot.bot.model.StateNames
import com.vk.logbot.bot.model.enm.Command
import com.vk.logbot.bot.service.BotApiMethodExecutor
import com.vk.logbot.bot.service.State
import com.vk.logbot.bot.service.StateContext
import com.vk.logbot.bot.util.KeyboardCreator

/**
 * Меню конфигураций.
 */
@BotState(StateNames.CONFIGURATIONS_MENU)
class ConfigurationsMenuState(
    stateContext: StateContext,
    botApiMethodExecutor: BotApiMethodExecutor,
    keyboardCreator: KeyboardCreator
) : State(
    stateContext, botApiMethodExecutor, keyboardCreator, linkedMapOf(
        Command.CREATE to StateNames.CREATE_CONFIGURATION_AWAITING_NAME_OR_FILE,
        Command.EDIT to StateNames.EDIT_CONFIGURATION_MENU,
        Command.REMOVE to StateNames.REMOVE_CONFIGURATION_MENU,
        Command.BACK to StateNames.MAIN_MENU
    )
)