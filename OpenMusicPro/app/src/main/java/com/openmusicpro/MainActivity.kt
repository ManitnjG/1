package com.openmusicpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.openmusicpro.presentation.navigation.OpenMusicNavHost
import com.openmusicpro.presentation.theme.OpenMusicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenMusicTheme { OpenMusicNavHost() }
        }
    }
}
