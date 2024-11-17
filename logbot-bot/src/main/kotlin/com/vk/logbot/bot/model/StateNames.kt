package com.vk.logbot.bot.model

/**
 * Строковые идентификаторы состояний бота.
 */
class StateNames {

    companion object {

        /**
         * Главное меню.
         */
        const val MAIN_MENU = "mainMenuState"

        /**
         * Меню конфигураций.
         */
        const val CONFIGURATIONS_MENU = "configurationsMenuState"

        /**
         * Создание новой конфигурации (ожидание ввода названия конфигурации).
         */
        const val CREATE_CONFIGURATION_AWAITING_NAME = "createConfigurationAwaitingNameState"

        /**
         * Создание конфигурации (ожидание ввода регулярного выражения).
         */
        const val CREATE_CONFIGURATION_AWAITING_REG_EXP = "createConfigurationAwaitingRegExpState"

        /**
         * Меню редактирования конфигураций.
         */
        const val EDIT_CONFIGURATION_MENU = "editConfigurationMenuState"

        /**
         * Редактирование конфигурации (ожидание ввода названия).
         */
        const val EDIT_CONFIGURATION_AWAITING_NAME = "editConfigurationAwaitingNameState"

        /**
         * Редактирование конфигурации (ожидание ввода регулярного выражения).
         */
        const val EDIT_CONFIGURATION_AWAITING_REG_EXP = "editConfigurationAwaitingRegExpState"

        /**
         * Меню удаления конфигурации.
         */
        const val REMOVE_CONFIGURATION_MENU = "removeConfigurationMenuState"
    }
}