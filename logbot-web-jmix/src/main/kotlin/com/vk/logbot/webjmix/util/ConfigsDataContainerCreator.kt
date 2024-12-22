package com.vk.logbot.webjmix.util

import io.jmix.flowui.model.DataComponents
import io.jmix.flowui.model.KeyValueCollectionContainer
import org.springframework.stereotype.Component

@Component
class ConfigsDataContainerCreator(
    private val dataComponents: DataComponents
) {
    fun createConfigsDataContainer(): KeyValueCollectionContainer {
        val container = dataComponents.createKeyValueCollectionContainer()
        container.addProperty(ConfigDataGridKeyValueEntity.ID, java.lang.Long::class.java)
        container.addProperty(ConfigDataGridKeyValueEntity.NAME)
        return container
    }
}