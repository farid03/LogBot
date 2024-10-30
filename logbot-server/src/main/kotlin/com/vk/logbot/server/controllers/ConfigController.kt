package com.vk.logbot.server.controllers

import com.vk.logbot.server.models.modelsDto.ConfigDto
import com.vk.logbot.server.models.modelsDto.CreatingConfigDto
import com.vk.logbot.server.models.modelsDto.EditConfigDto
import com.vk.logbot.server.services.ConfigService
import org.jetbrains.annotations.NotNull
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/configs")
class ConfigController(private val configService: ConfigService) {

    @GetMapping("/{id}")
    fun getConfigDtoById(@PathVariable("id") id: Long): ConfigDto {
        return configService.getConfigDtoById(id);
    }

    @GetMapping
    fun getConfigsByUserIdAndConfigName(@NotNull @RequestParam("user_id") userId: Long, @RequestParam("config_name") configName: String?): List<ConfigDto> {
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
    fun deleteConfig(@PathVariable("id") id: Long) {
        configService.deleteConfig(id);
    }
}