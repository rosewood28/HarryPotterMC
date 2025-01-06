package com.example.harrypottermc.ui.screns

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "house_selection") {
        composable("house_selection") {
            HouseSelectionScreen { selectedHouse ->
                navController.navigate("character_list/$selectedHouse")
            }
        }
        composable(
            "character_list/{house}",
            arguments = listOf(navArgument("house") { type = NavType.StringType })
        ) { backStackEntry ->
            val house = backStackEntry.arguments?.getString("house") ?: ""
            CharacterListScreen(house) { selectedCharacter ->
                navController.navigate("character_details/$selectedCharacter")
            }
        }
        composable(
            "character_details/{characterName}",
            arguments = listOf(navArgument("characterName") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterName = backStackEntry.arguments?.getString("characterName") ?: ""
            CharacterDetailsScreen(characterName)
        }
    }
}