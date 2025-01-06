package com.example.harrypottermc.ui.again

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypottermc.HarryPotterApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle(),
                harryPotterApplication().container.hpCharactersRepository)
        }

        initializer {
            HomeViewModel(harryPotterApplication().container.hpCharactersRepository)
        }
    }
}

fun CreationExtras.harryPotterApplication(): HarryPotterApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HarryPotterApplication)