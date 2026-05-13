package com.openmusicpro.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.openmusicpro.presentation.home.HomeScreen
import com.openmusicpro.presentation.library.LibraryScreen
import com.openmusicpro.presentation.player.PlayerScreen
import com.openmusicpro.presentation.search.SearchScreen

@Composable
fun OpenMusicNavHost() {
    val navController = rememberNavController()
    val tabs = listOf("home", "search", "library", "player")
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val backStack by navController.currentBackStackEntryAsState()
                tabs.forEach { route ->
                    NavigationBarItem(
                        selected = backStack?.destination?.route == route,
                        onClick = { navController.navigate(route) },
                        icon = { Text(route.first().uppercase()) },
                        label = { Text(route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen() }
                composable("search") { SearchScreen() }
                composable("library") { LibraryScreen() }
                composable("player") { PlayerScreen() }
            }
        }
    }
}
