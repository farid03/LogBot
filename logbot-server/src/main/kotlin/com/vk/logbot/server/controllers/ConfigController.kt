package com.vk.logbot.server.controllers

import com.vk.logbot.commons.dto.ConfigDto
import com.vk.logbot.commons.dto.CreatingConfigDto
import com.vk.logbot.commons.dto.EditConfigDto
import com.vk.logbot.server.services.ConfigService
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/configs")
class ConfigController(private val configService: ConfigService) {

    @GetMapping("/{id}")
    fun getConfigDtoById(@PathVariable("id") id: Long): ResponseEntity<ConfigDto> {
        try {
            return ResponseEntity.ok(configService.getConfigDtoById(id))
        } catch (ex:Exception){
            return ResponseEntity.badRequest().build()
        }
    }

    @GetMapping
    fun getConfigsByUserIdAndConfigName(
        @NotNull @RequestParam("user_id") userId: Long,
        @RequestParam("config_name") configName: String?
    ): ResponseEntity<List<ConfigDto>> {
        try {
            var configList = configService.getConfigsDtoByUserIdAndName(userId, configName)
            return ResponseEntity.ok(configList)
        } catch (ex:Exception){
            return ResponseEntity.badRequest().build()
        }
    }

    @PostMapping
    fun creatingConfig(@RequestBody creatingConfigDto: CreatingConfigDto): ResponseEntity<ConfigDto> {
        try{
            var config = configService.createConfig(creatingConfigDto)
            return ResponseEntity.ok(config)
        } catch (e: Exception){
            return ResponseEntity.badRequest().build()
        }
    }

    @PutMapping
    fun editConfig(@RequestBody editingConfigDto: EditConfigDto): ResponseEntity<ConfigDto> {
        try{
            var res = configService.editConfig(editingConfigDto)
            return ResponseEntity.ok(res)
        } catch (ex: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteConfig(@PathVariable("id") id: Long): ResponseEntity<String> {
        try {
            configService.deleteConfig(id)
            return ResponseEntity.ok().build()
        } catch (ex: Exception) {
            return ResponseEntity.badRequest().build()
        }
    }
}