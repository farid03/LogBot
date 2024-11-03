package com.vk.logbot.bot.service.cache

import com.vk.logbot.bot.config.CacheConfig
import com.vk.logbot.bot.enm.InputData
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import java.util.*

@Service
class InputDataCache(cacheManager: CacheManager) : AbstractCache<Long, EnumMap<InputData, String>>(
    cacheManager.getCache(CacheConfig.INPUT_DATA_CACHE) ?: throw NullPointerException()
) {
    fun getInputDataValue(chatId: Long, inputDataType: InputData): String? {
        val inputData = get(chatId) ?: return null
        return inputData[inputDataType]
    }

    fun putInputDataValue(chatId: Long, inputDataType: InputData, value: String) {
        var inputData = get(chatId)
        if (inputData == null) {
            inputData = EnumMap<InputData, String>(InputData::class.java)
            put(chatId, inputData)
        }
        inputData[inputDataType] = value
    }

    fun removeInputDataValue(chatId: Long, inputDataType: InputData) {
        var inputData = get(chatId)
        if (inputData == null) {
            inputData = EnumMap<InputData, String>(InputData::class.java)
        }
        inputData.remove(inputDataType)
    }
}