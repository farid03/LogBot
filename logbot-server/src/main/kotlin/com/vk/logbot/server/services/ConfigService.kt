package com.vk.logbot.server.services

import com.vk.logbot.server.models.Config
import com.vk.logbot.server.models.modelsDto.ConfigDto
import com.vk.logbot.server.models.modelsDto.CreatingConfigDto
import com.vk.logbot.server.models.modelsDto.EditConfigDto
import org.springframework.stereotype.Service

@Service
class ConfigService {

    fun convertConfigToDTO(config: Config): ConfigDto {
        return ConfigDto(config.regExp);
    }

    fun getConfigsDtoByUserId(userId: Int): List<ConfigDto> {
        return listOf(Config(1, 1, "errorName", "ERROR"), Config(2, 1, "debugName", "DEBUG"))
            .map { convertConfigToDTO(it) };
    }

    fun getConfigsDtoByUserIdAndName(userId: Int, configName: String): ConfigDto {
        return ConfigDto("ERROR");
    }

    fun getConfigDtoById(userId: Int): ConfigDto {
        return ConfigDto("DEBUG");
    }

    fun createConfig(creatingConfigDto: CreatingConfigDto): ConfigDto {
        return ConfigDto(creatingConfigDto.regExp);
    }

    fun editConfig(editConfigDto: EditConfigDto): ConfigDto {
        return ConfigDto(editConfigDto.regExp);
    }

    fun deleteConfig(id: Int): Boolean {
        return true;
    }
}