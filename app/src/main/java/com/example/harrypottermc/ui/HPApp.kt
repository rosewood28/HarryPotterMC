package com.example.harrypottermc.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.harrypottermc.ui.again.navigation.InventoryNavHost

/**
 * Top level composable that represents screens for the application.
 */
@Composable
fun HPApp(navController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navController)
}