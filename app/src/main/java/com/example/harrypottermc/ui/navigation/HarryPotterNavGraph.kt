package com.example.harrypottermc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.harrypottermc.ui.characterdetails.CharacterDetailsDestination
import com.example.harrypottermc.ui.characterdetails.CharacterDetailsScreen
import com.example.harrypottermc.ui.home.HomeDestination
import com.example.harrypottermc.ui.home.HomeScreen
import com.example.harrypottermc.ui.housecharacters.HouseCharactersDestination
import com.example.harrypottermc.ui.housecharacters.HouseCharactersScreen

@Composable
fun HarryPotterAppNavHost(navController: NavHostController) {
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