package com.example.harrypottermc.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypottermc.HarryPotterApplication
import com.example.harrypottermc.data.CharactersRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface HpUiState {
    data class Success(val characters: String) : HpUiState
    object Error : HpUiState
    object Loading : HpUiState
}

class HpViewModel(private val hpCharactersRepository: CharactersRepository): ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var hpUiState: HpUiState by mutableStateOf(HpUiState.Loading)
        private set

    /**
     * Call getCharacters() on init so we can display status immediately.
     */
    init {
        getHpCharacters()
    }

    /**
     * Gets characters information from the HP-API Retrofit service
     */
    fun getHpCharacters() {
        viewModelScope.launch {
            hpUiState = HpUiState.Loading
            hpUiState = try {
                val listResult = hpCharactersRepository.getCharacters()
                HpUiState.Success(
                    "Success: ${listResult.size} Harry Potter Characters retrieved: " +
                            "${listResult[0]}"
                )
            } catch (e: IOException) {
                HpUiState.Error
            } catch (e: HttpException) {
                HpUiState.Error
            }
        }
    }

    /**
     * Factory for [HpViewModel] that takes [hpCharactersRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HarryPotterApplication)
                val hpCharactersRepository = application.container.charactersRepository
                HpViewModel(hpCharactersRepository = hpCharactersRepository)
            }
        }
    }
}