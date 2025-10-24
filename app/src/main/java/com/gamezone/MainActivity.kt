package com.gamezone // Tu paquete base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamezone.ui.ExploreGamesScreen

import com.gamezone.ui.HomeScreen
import com.gamezone.ui.TopGamesScreen
import com.gamezone.ui.NoveltiesScreen
import com.gamezone.ui.theme.GameZoneTheme


object Destinations {
    const val HOME_ROUTE = "home"
    const val TOP_GAMES_ROUTE = "top_games"
    const val NOVELTIES_ROUTE = "novelties"

    const val EXPLORE_ROUTE = "explore_games"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameZoneTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.HOME_ROUTE
    ) {

        composable(Destinations.HOME_ROUTE) {
            HomeScreen(
                onNavigateToTopGames = {
                    navController.navigate(Destinations.TOP_GAMES_ROUTE)
                },
                onNavigateToNovelties = {
                    navController.navigate(Destinations.NOVELTIES_ROUTE)
                },
                onNavigateToExplore = { // <-- NUEVA NAVEGACIÓN
                    navController.navigate(Destinations.EXPLORE_ROUTE)
                }
            )
        }


        composable(Destinations.TOP_GAMES_ROUTE) {
            TopGamesScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }


        composable(Destinations.NOVELTIES_ROUTE) {
            NoveltiesScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }


        composable(Destinations.EXPLORE_ROUTE) {
            ExploreGamesScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
