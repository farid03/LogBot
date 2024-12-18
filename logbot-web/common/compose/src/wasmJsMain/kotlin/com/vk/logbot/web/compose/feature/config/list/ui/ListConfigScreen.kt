package com.vk.logbot.web.compose.feature.config.list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import com.vk.logbot.web.compose.feature.config.list.component.ListConfigDelegate
import com.vk.logbot.web.compose.feature.config.list.component.contract.ListConfigState
import com.vk.logbot.client.data.model.ConfigFile


@Composable
fun ListConfigScreen(state: ListConfigState, delegate: ListConfigDelegate) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item("empty") {
            AnimatedVisibility(visible = state.configs.isEmpty()) {
                Text("У вас пока нет конфигураций")
            }
        }
        items(state.configs.map { it.userId }) { c ->
            ItemListConfigScreen(
                modifier = Modifier.fillMaxWidth(),
                configFile = state.configs.first { it.userId == c },
                delegate = delegate
            )
        }
    }
}


@Composable
private fun ItemListConfigScreen(modifier: Modifier, configFile: ConfigFile, delegate: ListConfigDelegate) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = configFile.name)
            Text(text = configFile.message)
        }
        IconButton(onClick = { delegate.navigateToConfig(configFile) }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "arrow",
                modifier = Modifier.rotate(-90f),
                tint = Color.Black
            )
        }
    }
}
