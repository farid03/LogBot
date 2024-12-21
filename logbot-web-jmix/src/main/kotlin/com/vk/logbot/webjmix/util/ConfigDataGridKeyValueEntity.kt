package com.vk.logbot.webjmix.util

import com.vk.logbot.commons.dto.ConfigDto
import io.jmix.core.entity.KeyValueEntity

class ConfigDataGridKeyValueEntity(
    id: Long,
    name: String
) : KeyValueEntity() {

    companion object {
        const val ID = "id"
        const val NAME = "name"

        fun fromConfigDto(configDto: ConfigDto): ConfigDataGridKeyValueEntity {
            return ConfigDataGridKeyValueEntity(configDto.id, configDto.name)
        }
    }

    init {
        setValue(ID, id)
        setValue(NAME, name)
    }
}