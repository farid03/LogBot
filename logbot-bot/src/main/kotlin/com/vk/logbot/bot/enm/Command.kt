package com.vk.logbot.bot.enm

enum class Command(val text: String) {

    START("/start"),
    BACK("Назад"),
    CONFIG_FILES("Конфигурационные файлы"),
    UPLOAD("Создать"),
    RENAME("Переименовать"),
    EDIT_REG_EXP("Изменить регулярное выражение"),
    EDIT("Отредактировать"),
    REMOVE("Удалить"),
    REMOVE_ALL("Удалить все");

    companion object {
        fun getByText(text: String): Command? {
            return entries.firstOrNull { it.text == text }
        }
    }
}