package com.vk.logbot.bot.temp

import org.springframework.stereotype.Component

@Deprecated("Временный класс, удалить в будущем")
@Component
class ConfigDao(private val configs: ArrayList<Config> = ArrayList(), private var idCounter: Int = 0) {

    fun saveConfig(config: Config) {
        config.id = ++idCounter
        configs.add(config)
    }

    fun getConfigsByUserId(userId: Long): List<Config> {
        return configs.filter { it.ownerId == userId }
    }

    fun removeConfigById(id: Int) {
        configs.removeIf { it.id == id }
    }

    fun removeConfigsByUserId(userId: Long) {
        configs.removeIf { it.ownerId == userId }
    }

    fun renameConfigById(id: Int, newName: String) {
        configs.find { it.id == id }?.name = newName
    }

    fun editRegexpInConfigById(id: Int, newRegExp: String) {
        configs.find { it.id == id }?.regExp = newRegExp
    }

    fun editMessageInConfigById(id: Int, newMessage: String) {
        configs.find { it.id == id }?.message = newMessage
    }

    fun getConfigById(id: Int): Config? {
        return configs.find { it.id == id }
    }

    fun setActive(id: Int, active: Boolean) {
        configs.find { it.id == id }?.active = active
    }
}