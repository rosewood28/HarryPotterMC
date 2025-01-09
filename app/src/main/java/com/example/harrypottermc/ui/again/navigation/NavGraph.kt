package com.example.harrypottermc.ui.again.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.harrypottermc.ui.again.CharacterDetailsDestination
import com.example.harrypottermc.ui.again.CharacterDetailsScreen
import com.example.harrypottermc.ui.again.HomeDestination
import com.example.harrypottermc.ui.again.HomeScreen
import com.example.harrypottermc.ui.again.HouseCharactersDestination
import com.example.harrypottermc.ui.again.HouseCharactersScreen

@Composable
fun InventoryNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToCharactersBelongingToHouse = { houseName ->
                    navController.navigate("${HouseCharactersDestination.route}/$houseName")
                }
            )
        }

        composable(
            route = HouseCharactersDestination.routeWithArgs
        ) {
            HouseCharactersScreen(
                navigateToCharacterDetails = { characterId ->
                    navController.navigate("${CharacterDetailsDestination.route}/$characterId")
                },
            )
        }

        composable(
            route = CharacterDetailsDestination.routeWithCharacterId,
        ) {
            CharacterDetailsScreen()
        }
    }
}