package com.vk.logbot.webjmix.util

import com.vk.logbot.commons.dto.ConfigDto
import io.jmix.flowui.Dialogs
import io.jmix.flowui.app.inputdialog.DialogOutcome
import io.jmix.flowui.app.inputdialog.InputDialog
import io.jmix.flowui.app.inputdialog.InputDialog.InputDialogCloseEvent
import io.jmix.flowui.app.inputdialog.InputParameter
import io.jmix.flowui.component.validation.ValidationErrors
import io.jmix.flowui.view.View
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class ConfigDataGridDialogsCreator(
    private val dialogs: Dialogs,
    private val regExpValidator: RegExpValidator
) {
    companion object {
        const val NAME = "name"
        const val REG_EXP = "regExp"
        const val MESSAGE = "message"
        const val IS_ACTIVE = "isActive"
    }

    fun openCreateDialog(originView: View<*>, onOkAction: Consumer<InputDialogCloseEvent>) {
        dialogs.createInputDialog(originView)
            .withHeader("Задайте параметры конфигурации")
            .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
            .withParameters(
                InputParameter.stringParameter(NAME)
                    .withLabel("Название")
                    .withRequired(true),

                InputParameter.stringParameter(REG_EXP)
                    .withLabel("Регулярное выражение")
                    .withRequired(true),

                InputParameter.stringParameter(MESSAGE)
                    .withLabel("Сообщение")
                    .withRequired(true)
            )
            .withValidator(this::validateRegExp)
            .withCloseListener { closeEvent ->
                if (!closeEvent.closedWith(DialogOutcome.OK)) {
                    return@withCloseListener
                }
                onOkAction.accept(closeEvent)
            }
            .open()
    }

    fun openEditDialog(originView: View<*>, config: ConfigDto, onOkAction: Consumer<InputDialogCloseEvent>) {
        dialogs.createInputDialog(originView)
            .withHeader("Задайте параметры конфигурации")
            .withLabelsPosition(Dialogs.InputDialogBuilder.LabelsPosition.TOP)
            .withParameters(
                InputParameter.stringParameter(NAME)
                    .withLabel("Название")
                    .withRequired(true)
                    .withDefaultValue(config.name),

                InputParameter.stringParameter(REG_EXP)
                    .withLabel("Регулярное выражение")
                    .withRequired(true)
                    .withDefaultValue(config.regExp),

                InputParameter.stringParameter(MESSAGE)
                    .withLabel("Сообщение")
                    .withRequired(true)
                    .withDefaultValue(config.message),

                InputParameter.booleanParameter(IS_ACTIVE)
                    .withLabel("Активна")
                    .withRequired(true)
                    .withDefaultValue(config.active)
            )
            .withValidator(this::validateRegExp)
            .withCloseListener { closeEvent ->
                if (!closeEvent.closedWith(DialogOutcome.OK)) {
                    return@withCloseListener
                }
                onOkAction.accept(closeEvent)
            }
            .open()
    }

    private fun validateRegExp(context: InputDialog.ValidationContext): ValidationErrors {
        val regExp = context.getValue<String>(REG_EXP) ?: return ValidationErrors.none()
        return regExpValidator.validateRegExp(regExp)
    }
}