package com.example.wordle.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordle.ui.views.WordleGameScreen


@Composable
fun Navigator() {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "game_screen"
        ) {
            // Pantalla principal del juego
            composable("game_screen") {
                WordleGameScreen(navController = navController)
            }

            //
        }
}