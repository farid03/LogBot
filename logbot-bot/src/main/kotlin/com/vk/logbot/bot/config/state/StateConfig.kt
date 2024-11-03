package com.vk.logbot.bot.config.state

import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.service.LogBot
import com.vk.logbot.bot.service.cache.InputDataCache
import com.vk.logbot.bot.service.external.ConfigFileService
import com.vk.logbot.bot.service.state.*
import com.vk.logbot.bot.util.CallbackUtils
import com.vk.logbot.bot.util.FileDownloader
import com.vk.logbot.bot.util.InlineKeyboardMaker
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import java.util.*

@Configuration
class StateConfig(
    @Qualifier("mainKeyboard") private val mainKeyboard: ReplyKeyboardMarkup,
    @Qualifier("configFilesKeyboard") private val configFileKeyboard: ReplyKeyboardMarkup,
    @Qualifier("editConfigFileKeyboard") private val editConfigFileKeyboard: ReplyKeyboardMarkup,
    @Qualifier("removeConfigFileKeyboard") private val removeConfigFileKeyboard: ReplyKeyboardMarkup,
    @Qualifier("backButtonKeyboard") private val backButtonKeyboard: ReplyKeyboardMarkup,
    private val fileDownloader: FileDownloader,
    private val configFileService: ConfigFileService,
    private val inputDataCache: InputDataCache,
    private val logBot: LogBot,
    private val inlineKeyboardMaker: InlineKeyboardMaker,
    private val callbackUtils: CallbackUtils
) {
    @Bean
    fun authorizedState(): AuthorizedState {
        return AuthorizedState(
            null, EnumMap(Command::class.java), mainKeyboard
        )
    }

    @Bean
    fun inConfigFilesMenuState(): InConfigFilesMenuState {
        return InConfigFilesMenuState(
            null, EnumMap(Command::class.java), configFileKeyboard
        )
    }

    @Bean
    fun uploadConfigFileState(): UploadConfigFileState {
        return UploadConfigFileState(
            null,
            EnumMap(Command::class.java),
            backButtonKeyboard,
            fileDownloader,
            configFileService,
            inputDataCache
        )
    }

    @Bean
    fun editConfigFileState(): EditConfigFileState {
        return EditConfigFileState(
            null,
            EnumMap(Command::class.java),
            backButtonKeyboard,
            editConfigFileKeyboard,
            fileDownloader,
            configFileService,
            inputDataCache,
            logBot,
            inlineKeyboardMaker,
            callbackUtils
        )
    }

    @Bean
    fun removeConfigFileState(): RemoveConfigFileState {
        return RemoveConfigFileState(
            null,
            EnumMap(Command::class.java),
            removeConfigFileKeyboard,
            configFileService,
            logBot,
            inlineKeyboardMaker,
            callbackUtils
        )
    }
}