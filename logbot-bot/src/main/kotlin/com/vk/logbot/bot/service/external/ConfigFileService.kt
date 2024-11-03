package com.vk.logbot.bot.service.external

import com.vk.logbot.bot.exception.BotConfigFileNotValidException
import com.vk.logbot.bot.exception.BotConfigNotFoundException
import com.vk.logbot.bot.model.ConfigFile
import org.springframework.stereotype.Service
import java.io.File

//TODO: временная тестовая логика, убрать после реализации на бэкенде
@Service
class ConfigFileService {

    private val configFiles = ArrayList<ConfigFile>()

    fun createConfigFile(name: String, regExp: String, userId: Long): ConfigFile {
        val configFile = ConfigFile(name, regExp, userId)
        configFiles.add(configFile)
        return configFile
    }

    fun uploadConfigFile(configFile: File, userId: Long, chatId: Long) {
        throw BotConfigFileNotValidException(chatId)
    }

    fun getConfigFiles(userId: Long): List<ConfigFile> {
        return configFiles.filter { configFile -> configFile.userId == userId }
    }

    fun getConfigFile(userId: Long, name: String): ConfigFile? {
        return configFiles.find { configFile -> configFile.userId == userId && configFile.name == name }
    }

    fun removeConfigFile(userId: Long, name: String, chatId: Long) {
        val configFile = configFiles.find { configFile -> configFile.userId == userId }
        if (configFile != null) {
            configFiles.remove(configFile)
        } else {
            throw BotConfigNotFoundException(name, chatId)
        }
    }

    fun removeConfigFiles(userId: Long) {
        configFiles.removeIf { configFile -> configFile.userId == userId }
    }
}