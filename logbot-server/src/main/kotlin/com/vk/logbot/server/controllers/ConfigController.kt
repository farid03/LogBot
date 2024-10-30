package com.vk.logbot.server.controllers

import com.vk.logbot.server.models.modelsDto.ConfigDto
import com.vk.logbot.server.models.modelsDto.CreatingConfigDto
import com.vk.logbot.server.models.modelsDto.EditConfigDto
import com.vk.logbot.server.services.ConfigService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/configs")
class ConfigController(private val configService: ConfigService) {

    @GetMapping("/user/{user_id}")
    fun getConfigsByUserId(@PathVariable("user_id") userId: Int): List<ConfigDto> {
        return configService.getConfigsDtoByUserId(userId);
    }

    @GetMapping("/{id}")
    fun getConfigDtoById(@PathVariable("id") id: Int): ConfigDto {
        return configService.getConfigDtoById(id);
    }

    @GetMapping("/user/{user_id}/{config_name}")
    fun getConfigsByUserIdAndConfigName(userId: Int, configName: String): ConfigDto {
        return configService.getConfigsDtoByUserIdAndName(userId, configName);
    }

    @PostMapping
    fun creatingConfig(@RequestBody creatingConfigDto: CreatingConfigDto): ConfigDto {
        return configService.createConfig(creatingConfigDto);
    }

    @PutMapping
    fun editConfig(@RequestBody editingConfigDto: EditConfigDto): ConfigDto {
        return configService.editConfig(editingConfigDto);
    }

    @DeleteMapping("/{id}")
    fun deleteConfig(@PathVariable("id") id: Int) {
        configService.deleteConfig(id);
    }
}