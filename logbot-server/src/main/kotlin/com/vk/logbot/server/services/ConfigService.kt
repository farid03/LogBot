package com.vk.logbot.server.services

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.server.models.Config
import com.vk.logbot.commons.dto.CreatingConfigDto
import com.vk.logbot.commons.dto.EditConfigDto
import com.vk.logbot.server.repositories.ConfigRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class ConfigService(private val configRepository: ConfigRepository) {

    private val modelMapper: ModelMapper = ModelMapper()

    fun convertConfigToDto(config: Config?): ConfigDto {
        if (config == null) return ConfigDto(0, "name", "none")
        return modelMapper.map(config, ConfigDto::class.java)
    }

    fun convertCreatingConfigDtoToConfig(creatingConfigDto: CreatingConfigDto): Config {
        return modelMapper.map(creatingConfigDto, Config::class.java)
    }

    fun getConfigsDtoByUserIdAndName(userId: Long, configName: String?): List<ConfigDto> {
        if (configName != null) {
            return configRepository.findConfigsByNameAndUserId(configName, userId).map { convertConfigToDto(it) }
        }
        return configRepository.findConfigsByUserId(userId).map { convertConfigToDto(it) }
    }

    fun getConfigDtoById(configId: Long): ConfigDto {
        return convertConfigToDto(configRepository.findConfigById(configId))
    }

    fun createConfig(creatingConfigDto: CreatingConfigDto): ConfigDto {
        return convertConfigToDto(configRepository.save(convertCreatingConfigDtoToConfig(creatingConfigDto)))
    }

    fun editConfig(editConfigDto: EditConfigDto): ConfigDto {
        val number = configRepository.updateConfigById(editConfigDto, editConfigDto.id)
        return convertConfigToDto(Config(1, 1,"null", "null"))
    }

    fun deleteConfig(id: Long) {
        return configRepository.deleteById(id)
    }
}