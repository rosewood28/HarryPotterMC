package com.example.harrypottermc.ui

import android.view.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.harrypottermc.ui.screens.HomeScreen
import com.example.harrypottermc.ui.screens.HpViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HarryPotterApp() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val hpViewModel: HpViewModel = viewModel(factory = HpViewModel.Factory)
        HomeScreen(
            hpUiState = hpViewModel.hpUiState,
        )
    }

}