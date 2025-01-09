package com.example.harrypottermc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.harrypottermc.ui.navigation.HarryPotterAppNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun HarryPotterApp(navController: NavHostController = rememberNavController()) {
    HarryPotterAppNavHost(navController = navController)
}