package com.user.moviesapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.user.data.model.MediaItem
import com.user.moviesapp.ui.detailscreen.DetailScreen
import com.user.moviesapp.ui.mainscreen.MainScreen
import com.user.moviesapp.ui.playerscreen.PlayerScreen

const val ROUTE_MAIN_SCREEN = "main_screen"
const val ROUTE_DETAIL_SCREEN = "detail_screen"
const val ROUTE_PLAYER_SCREEN = "player_screen"
const val MEDIA_ITEM = "media_item"

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    padding: PaddingValues
) {
    NavHost(navController = navController, startDestination = ROUTE_MAIN_SCREEN) {
        composable("main_screen") {
            MainScreen(modifier = Modifier.padding(padding)) { mediaItem ->
                navigateToDetailScreen(navController, mediaItem)
            }
        }

        composable(
            route = ROUTE_DETAIL_SCREEN
        ) {
            val mediaItem: MediaItem? =
                navController.previousBackStackEntry?.savedStateHandle?.get(MEDIA_ITEM)
            mediaItem?.let {
                DetailScreen(it) {
                    navigateToPlayerScreen(navController)
                }
            }
        }

        composable(ROUTE_PLAYER_SCREEN) {
            PlayerScreen()
        }
    }
}

fun navigateToDetailScreen(navController: NavHostController, mediaItem: MediaItem) {
    navController.currentBackStackEntry?.savedStateHandle?.set(MEDIA_ITEM, mediaItem)
    navController.navigate(ROUTE_DETAIL_SCREEN)
}

fun navigateToPlayerScreen(navController: NavHostController) {
    navController.navigate(ROUTE_PLAYER_SCREEN)
}
