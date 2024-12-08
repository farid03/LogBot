package com.vk.logbot.web.feature.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vk.logbot.web.feature.main.component.MainDelegate
import com.vk.logbot.web.feature.main.component.contract.MainState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    state: MainState,
    delegate: MainDelegate
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Конфигурационные файлы")
        Button(onClick = { delegate.navigateToConfigFiles() }) {
            Text("Мои файлы")
        }
        Button(onClick = { delegate.navigateToCreateConfigFile() }) {
            Text("Создать файлы")
        }
    }

}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        state = MainState(),
        object : MainDelegate {
            override fun navigateToConfigFiles() {

            }

            override fun navigateToCreateConfigFile() {}
        },
    )
}