package com.vk.logbot.web.feature.splash.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.vk.logbot.web.feature.splash.component.contract.SplashState

@Composable
fun SplashScreen(state: SplashState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            state.isLoading
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
    }
}