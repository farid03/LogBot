package com.vk.logbot.server.services

import com.vk.logbot.server.models.Config
import com.vk.logbot.server.models.modelsDto.ConfigDto
import com.vk.logbot.server.models.modelsDto.CreatingConfigDto
import com.vk.logbot.server.models.modelsDto.EditConfigDto
import org.springframework.stereotype.Service

@Service
class ConfigService {

    private val configs: List<Config> = listOf(Config(1, 1, "errorName", "ERROR"), Config(2, 1, "debugName", "DEBUG"));

    fun convertConfigToDTO(config: Config): ConfigDto {
        return ConfigDto(config.regExp);
    }

    fun getConfigsDtoByUserIdAndName(userId: Long, configName: String?): List<ConfigDto> {
        if (configName != null) {
            return configs.filter { config -> config.name == configName }.map { convertConfigToDTO(it) };
        }
        return listOf(Config(1, 1, "errorName", "ERROR"), Config(2, 1, "debugName", "DEBUG"))
            .map { convertConfigToDTO(it) };
    }

    fun getConfigDtoById(userId: Long): ConfigDto {
        return ConfigDto("DEBUG");
    }

    fun createConfig(creatingConfigDto: CreatingConfigDto): ConfigDto {
        return ConfigDto(creatingConfigDto.regExp);
    }

    fun editConfig(editConfigDto: EditConfigDto): ConfigDto {
        return ConfigDto(editConfigDto.regExp);
    }

    fun deleteConfig(id: Long): Boolean {
        return true;
    }
}