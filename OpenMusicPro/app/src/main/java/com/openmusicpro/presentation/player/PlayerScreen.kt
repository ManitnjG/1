package com.openmusicpro.presentation.player

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember

@Composable
fun PlayerScreen() {
    val speed = remember { mutableFloatStateOf(1f) }
    Column {
        Text("Mini-player / full player / waveform / lyrics sync placeholder")
        Slider(value = speed.floatValue, onValueChange = { speed.floatValue = it }, valueRange = 0.5f..2f)
        Text("Speed: ${speed.floatValue}")
    }
}
