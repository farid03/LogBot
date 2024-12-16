package com.vk.logbot.server.services

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.server.models.Config
import com.vk.logbot.commons.dto.CreatingConfigDto
import com.vk.logbot.commons.dto.EditConfigDto
import com.vk.logbot.server.exceptions.ServiceException
import com.vk.logbot.server.exceptions.UserException
import com.vk.logbot.server.repositories.ConfigRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class ConfigService(private val configRepository: ConfigRepository) {

    companion object : KLogging()

    fun convertConfigToDto(config: Config): ConfigDto {
        return ConfigDto(config.id, config.userId, config.name, config.regExp, config.message, config.active)
    }

    fun convertCreatingConfigDtoToConfig(creatingConfigDto: CreatingConfigDto): Config {
        return Config(
            userId = creatingConfigDto.userId,
            name = creatingConfigDto.name,
            regExp = creatingConfigDto.regExp,
            message = creatingConfigDto.message
        )
    }

    fun getConfigsDtoByUserIdAndName(userId: Long, configName: String?): List<ConfigDto> {
        try {
            if (configName != null) {
                return configRepository.findConfigsByNameAndUserId(configName, userId).map { convertConfigToDto(it) }
            }
            return configRepository.findConfigsByUserId(userId).map { convertConfigToDto(it) }
        } catch (ex: Exception) {
            logger.warn { ex.message }
            throw ServiceException("Ошибка сервиса")
        }
    }

    fun getConfigDtoById(configId: Long): ConfigDto {
        try {
            val config = configRepository.findConfigById(configId)
            config?.let { return convertConfigToDto(it) }
            throw UserException("Конфиг не найден")
        } catch (ex: Exception) {
            logger.warn { ex.message }
            throw ServiceException("Ошибка сервиса")
        }
    }

    fun createConfig(creatingConfigDto: CreatingConfigDto): ConfigDto {
        try {
            var config = configRepository.save(convertCreatingConfigDtoToConfig(creatingConfigDto))
            return convertConfigToDto(config)
        } catch (ex: Exception) {
            logger.warn { ex.message }
            throw ServiceException("Ошибка сервиса")
        }
    }

    fun editConfig(editConfigDto: EditConfigDto): ConfigDto {
        try {
            var config = configRepository.findConfigById(editConfigDto.id)
            config?.let {
                var value = it
                value.name = editConfigDto.name
                value.regExp = editConfigDto.regExp
                value.message = editConfigDto.message
                value.active = editConfigDto.active
                return convertConfigToDto(configRepository.save(value))
            }
            throw UserException("Конфиг не найден")
        } catch (ex: Exception) {
            logger.warn { ex.message }
            throw ServiceException("Ошибка сервиса")
        }
    }

    fun deleteConfig(id: Long) {
        try {
            configRepository.deleteById(id)
        } catch (ex: RuntimeException) {
            logger.warn { ex.message }
            throw UserException("Ошибка при удалении")
        }
    }

    fun getActiveConfigs(): List<Config> =
        configRepository.findConfigsByActive(true)
}