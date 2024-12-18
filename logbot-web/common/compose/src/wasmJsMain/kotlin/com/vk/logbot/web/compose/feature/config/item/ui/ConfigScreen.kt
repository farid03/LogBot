package com.vk.logbot.web.compose.feature.config.item.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vk.logbot.web.compose.feature.config.item.component.ConfigDelegate
import com.vk.logbot.web.compose.feature.config.item.component.contract.ConfigState

@Composable
fun ConfigScreen(
    state: ConfigState,
    delegate: ConfigDelegate
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = state.configFile.name,
            onValueChange = delegate::updateName,
            placeholder = { Text("Название") },
        )

        OutlinedTextField(
            value = state.configFile.regular,
            onValueChange = delegate::updateRegular,
            placeholder = { Text("Регулярное выражение") },
        )

        OutlinedTextField(
            value =state.configFile.message ,
            onValueChange = delegate::updateMessage,
            placeholder = { Text("Вид сообщения") },
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!state.isCreating)
                Button(
                    onClick = { delegate.deleteConfig() }

                ) {
                    Text("Удалить конфигурацию",)
                }
            Button(
                onClick = { delegate.save() }

            ) {
                Text("Сохранить")
            }

        }


    }

}