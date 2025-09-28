package com.hajjcompanion.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hajjcompanion.feature.rituals.RitualDetailScreen
import com.hajjcompanion.feature.rituals.RitualsScreen
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    MaterialTheme {
        val nav = rememberNavController()
        Scaffold { padding ->
            NavHost(
                navController = nav,
                startDestination = "rituals",
            ) {
                composable("rituals") {
                    RitualsScreen(onRitualClick = { id -> nav.navigate("ritual/${'$'}id") })
                }
                composable(
                    route = "ritual/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id") ?: return@composable
                    RitualDetailScreen(ritualId = id)
                }
            }
        }
    }
}
