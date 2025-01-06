package com.example.harrypottermc.ui.again.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.harrypottermc.ui.again.AppViewModelProvider
import com.example.harrypottermc.ui.again.HomeDestination
import com.example.harrypottermc.ui.again.HomeScreen
import com.example.harrypottermc.ui.again.ItemDetailsDestination
import com.example.harrypottermc.ui.again.ItemDetailsScreen

@Composable
fun InventoryNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemDetails = { itemId ->
                    navController.navigate("${ItemDetailsDestination.route}/$itemId")
                }
            )
        }
        composable(route = ItemDetailsDestination.routeWithArgs) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(ItemDetailsDestination.itemIdArg)?.toInt() ?: 0
            ItemDetailsScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}