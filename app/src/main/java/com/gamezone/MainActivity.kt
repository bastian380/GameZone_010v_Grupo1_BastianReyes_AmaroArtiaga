package com.gamezone // Tu paquete base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
// --- Asegúrate de que TODOS estos imports existan y apunten a la ubicación correcta ---
import com.gamezone.ui.screens.exploregames.ExploreGamesScreen
import com.gamezone.ui.screens.home.HomeScreen
import com.gamezone.ui.screens.topgames.TopGamesScreen
import com.gamezone.ui.screens.novelties.NoveltiesScreen
import com.gamezone.ui.theme.GameZoneTheme
import com.gamezone.ui.screens.createuser.CreateUserScreen  // Import necesario

object Destinations {
    const val HOME_ROUTE = "home"
    const val TOP_GAMES_ROUTE = "top_games"
    const val NOVELTIES_ROUTE = "novelties"
    const val EXPLORE_ROUTE = "explore_games"
    const val CREATE_USER_ROUTE = "create_user"
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
                onNavigateToExplore = {
                    navController.navigate(Destinations.EXPLORE_ROUTE)
                },
                onNavigateToCreateUser = {
                    navController.navigate(Destinations.CREATE_USER_ROUTE)
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

        composable(Destinations.CREATE_USER_ROUTE) {
            CreateUserScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
