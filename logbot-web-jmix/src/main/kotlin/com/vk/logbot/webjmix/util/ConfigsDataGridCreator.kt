package com.vk.logbot.webjmix.util

import com.vaadin.flow.component.grid.Grid
import io.jmix.core.entity.KeyValueEntity
import io.jmix.flowui.UiComponents
import io.jmix.flowui.component.grid.DataGrid
import io.jmix.flowui.data.grid.ContainerDataGridItems
import io.jmix.flowui.model.KeyValueCollectionContainer
import org.springframework.stereotype.Component

@Component
class ConfigsDataGridCreator(
    private val uiComponents: UiComponents
) {
    @Suppress("UNCHECKED_CAST")
    fun createConfigsDataGrid(container: KeyValueCollectionContainer): DataGrid<KeyValueEntity> {
        val dataGrid = uiComponents.create(DataGrid::class.java) as DataGrid<KeyValueEntity>

        dataGrid.addColumn(
            ConfigDataGridKeyValueEntity.ID,
            container.entityMetaClass.getPropertyPath(ConfigDataGridKeyValueEntity.ID)!!
        )
            .setHeader("ID")
            .setFlexGrow(0)
            .setSortable(false)

        dataGrid.addColumn(
            ConfigDataGridKeyValueEntity.NAME,
            container.entityMetaClass.getPropertyPath(ConfigDataGridKeyValueEntity.NAME)!!
        )
            .setHeader("Название")
            .setSortable(false)

        dataGrid.setSelectionMode(Grid.SelectionMode.SINGLE)
        dataGrid.isColumnReorderingAllowed = true

        dataGrid.setItems(ContainerDataGridItems(container))

        return dataGrid
    }
}