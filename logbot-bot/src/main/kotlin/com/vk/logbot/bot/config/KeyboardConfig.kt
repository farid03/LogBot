package com.vk.logbot.bot.config

import com.vk.logbot.bot.enm.Command
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

@Configuration
class KeyboardConfig {

    @Bean
    @Qualifier("mainKeyboard")
    fun mainKeyboard(): ReplyKeyboardMarkup {
        val row = KeyboardRow()
        row.add(KeyboardButton(Command.CONFIG_FILES.text))

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = listOf(row)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }

    @Bean
    @Qualifier("configFilesKeyboard")
    fun configFilesKeyboard(): ReplyKeyboardMarkup {
        val row1 = KeyboardRow()
        row1.add(KeyboardButton(Command.UPLOAD.text))
        row1.add(KeyboardButton(Command.EDIT.text))

        val row2 = KeyboardRow()
        row2.add(KeyboardButton(Command.REMOVE.text))
        row2.add(KeyboardButton(Command.BACK.text))

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = listOf(row1, row2)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }

    @Bean
    @Qualifier("editConfigFileKeyboard")
    fun editConfigFileKeyboard(): ReplyKeyboardMarkup {
        val row1 = KeyboardRow()
        row1.add(KeyboardButton(Command.RENAME.text))
        row1.add(KeyboardButton(Command.EDIT_REG_EXP.text))

        val row2 = KeyboardRow()
        row2.add(KeyboardButton(Command.BACK.text))

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = listOf(row1, row2)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }

    @Bean
    @Qualifier("removeConfigFileKeyboard")
    fun removeConfigFileKeyboard(): ReplyKeyboardMarkup {
        val row = KeyboardRow()
        row.add(KeyboardButton(Command.REMOVE_ALL.text))
        row.add(KeyboardButton(Command.BACK.text))

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = listOf(row)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }

    @Bean
    @Qualifier("backButtonKeyboard")
    fun backButtonKeyboard(): ReplyKeyboardMarkup {
        val row = KeyboardRow()
        row.add(KeyboardButton(Command.BACK.text))

        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.keyboard = listOf(row)
        replyKeyboardMarkup.selective = true
        replyKeyboardMarkup.resizeKeyboard = true
        return replyKeyboardMarkup
    }
}