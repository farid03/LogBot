package com.vk.logbot.bot.config.state

import com.vk.logbot.bot.enm.Command
import com.vk.logbot.bot.service.state.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

@Configuration
@DependsOn("stateConfig")
class StateInitializer(
    private val authorizedState: AuthorizedState,
    private val inConfigFilesMenuState: InConfigFilesMenuState,
    private val uploadConfigFileState: UploadConfigFileState,
    private val editConfigFileState: EditConfigFileState,
    private val removeConfigFileState: RemoveConfigFileState
) {
    @PostConstruct
    fun init() {
        authorizedState.nextStates[Command.CONFIG_FILES] = inConfigFilesMenuState

        inConfigFilesMenuState.previewState = authorizedState
        inConfigFilesMenuState.nextStates[Command.UPLOAD] = uploadConfigFileState
        inConfigFilesMenuState.nextStates[Command.EDIT] = editConfigFileState
        inConfigFilesMenuState.nextStates[Command.REMOVE] = removeConfigFileState

        uploadConfigFileState.previewState = inConfigFilesMenuState

        editConfigFileState.previewState = inConfigFilesMenuState

        removeConfigFileState.previewState = inConfigFilesMenuState
    }
}