package com.vk.logbot.bot.model.enm

/**
 * Типы даных, сохраняемых в кэше.
 */
enum class CacheDataType {

    /**
     * Название создаваемой конфигурации.
     */
    CREATABLE_CONFIGURATION_NAME,

    /**
     * Регулярное выражение создаваемой конфигурации.
     */
    CREATABLE_CONFIGURATION_REG_EXP,

    /**
     * ID изменяемой конфигурации.
     */
    EDITABLE_CONFIGURATION_ID;
}