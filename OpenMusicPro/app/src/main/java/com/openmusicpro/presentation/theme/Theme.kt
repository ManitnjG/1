package com.openmusicpro.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Light = lightColorScheme()
private val Dark = darkColorScheme()

@Composable
fun OpenMusicTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = Dark, content = content)
}
