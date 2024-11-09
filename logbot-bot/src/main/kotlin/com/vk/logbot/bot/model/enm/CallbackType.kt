package com.vk.logbot.bot.model.enm

/**
 * Тип коллбэка.
 */
enum class CallbackType {

    /**
     * Выбор конфигурации для переименования.
     */
    EDIT_CONFIGURATION_MENU_CHOICE_FOR_RENAME,

    /**
     * Выбор конфигурации для изменения регулярного выражения.
     */
    EDIT_CONFIGURATION_MENU_CHOICE_FOR_EDIT_REG_EXP,

    /**
     * Выбор конфигурации для удаления.
     */
    REMOVE_CONFIGURATION_MENU_CHOICE;

    companion object {
        fun getByOrdinal(ordinal: Int): CallbackType {
            return entries[ordinal]
        }
    }
}