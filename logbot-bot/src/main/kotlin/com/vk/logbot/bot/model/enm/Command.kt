package com.vk.logbot.bot.model.enm

/**
 * Текстовые команды.
 */
enum class Command(val text: String) {

    START("/start"),
    BACK("Назад"),
    MAIN_MENU("Главное меню"),
    CONFIGURATIONS("Конфигурации"),
    CREATE("Создать"),
    EDIT("Редактировать"),
    RENAME("Переименовать"),
    EDIT_REG_EXP("Изменить регулярное выражение"),
    REMOVE("Удалить"),
    REMOVE_ALL("Удалить все");

    companion object {
        /**
         * Возвращает команду по текстовому представлению.
         */
        fun getByText(text: String?): Command? {
            return entries.find { it.text == text }
        }
    }
}