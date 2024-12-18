package com.vk.logbot.web.compose.feature.config.list.component


import com.vk.logbot.client.data.model.ConfigFile
import com.vk.logbot.client.domain.usecases.config.*
import com.vk.logbot.web.compose.mvi.component.BaseFeature
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigAction
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigIntent
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigNavigation
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ListConfigFeature() : BaseFeature<ListConfigState, ListConfigIntent, ListConfigAction, ListConfigReducer>(),
    KoinComponent {

    override val reducer: ListConfigReducer by inject<ListConfigReducer>()
    private val getAllConfigUseCase: IGetAllConfigUseCase by inject<IGetAllConfigUseCase>()
    override fun initState(): ListConfigState = ListConfigState()

    init {
        getConfigsFiles()
    }

    private fun getConfigsFiles() {
        onAction(
            ListConfigAction.SetConfigs(
                listOf(

                )
            )
        )
    }

    override fun handleIntent(intent: ListConfigIntent) {
        when (intent) {
            is ListConfigIntent.ShowConfigFile -> showConfig(intent.config)
        }
    }

    private fun showConfig(config: ConfigFile) {
        onNavigation(ListConfigNavigation.Config(config))
    }

}