package com.example.harrypottermc.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.harrypottermc.ui.screens.HomeScreen
import com.example.harrypottermc.ui.screens.HpViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HarryPotterApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val hpViewModel: HpViewModel = viewModel(factory = HpViewModel.Factory)

        // Collect StateFlow as State
        val hpUiState by hpViewModel.hpUiState.collectAsState()

        // Pass the collected state to HomeScreen
        HomeScreen(
            hpUiState = hpUiState,
        )
    }

}