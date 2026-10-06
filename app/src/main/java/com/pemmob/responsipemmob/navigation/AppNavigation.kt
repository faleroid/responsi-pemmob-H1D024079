package com.pemmob.responsipemmob.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemmob.responsipemmob.ui.screen.DetailScreen
import com.pemmob.responsipemmob.ui.screen.HomeScreen
import com.pemmob.responsipemmob.ui.viewmodel.GempaViewModel

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{index}"
    fun detail(index: Int) = "detail/$index"
}

@Composable
fun AppNavigation(viewModel: GempaViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onGempaClick = { index ->
                    navController.navigate(Routes.detail(index))
                }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: -1
            DetailScreen(
                gempa = viewModel.getGempaByIndex(index),
                onBack = { navController.popBackStack() }
            )
        }
    }
}