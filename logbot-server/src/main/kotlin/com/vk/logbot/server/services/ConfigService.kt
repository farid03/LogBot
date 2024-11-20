package com.vk.logbot.server.services

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.server.models.Config
import com.vk.logbot.commons.dto.CreatingConfigDto
import com.vk.logbot.commons.dto.EditConfigDto
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service

@Service
class ConfigService {

    private val modelMapper: ModelMapper = ModelMapper()
    private val configs: List<Config> = listOf(Config(1, 1, "errorName", "ERROR"), Config(2, 1, "debugName", "DEBUG"))

    fun convertConfigToDto(config: Config): ConfigDto {
        return modelMapper.map(config, ConfigDto::class.java)
    }

    fun getConfigsDtoByUserIdAndName(userId: Long, configName: String?): List<ConfigDto> {
        if (configName != null) {
            return configs.filter { config -> config.name == configName }.map { convertConfigToDto(it) }
        }
        return listOf(Config(1, 1, "errorName", "ERROR"), Config(2, 1, "debugName", "DEBUG"))
            .map { convertConfigToDto(it) }
    }

    fun getConfigDtoById(userId: Long): ConfigDto {
        return ConfigDto(2, "DEBUG", "DEBUG")
    }

    fun createConfig(creatingConfigDto: CreatingConfigDto): ConfigDto {
        return ConfigDto(3, "NAME", creatingConfigDto.regExp)
    }

    fun editConfig(editConfigDto: EditConfigDto): ConfigDto {
        return ConfigDto(4, "NAME", editConfigDto.regExp)
    }

    fun deleteConfig(id: Long): Boolean {
        return true
    }
}