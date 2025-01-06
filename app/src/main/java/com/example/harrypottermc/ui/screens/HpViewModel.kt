package com.example.harrypottermc.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harrypottermc.HarryPotterApplication
import com.example.harrypottermc.data.HpCharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

class HpViewModel(
    private val hpCharactersRepository: HpCharactersRepository
): ViewModel() {
    /** The MutableStateFlow that stores the status of the most recent request */
    private val _hpUiState = MutableStateFlow<HpUiState>(HpUiState.Loading)
    val hpUiState: StateFlow<HpUiState> = _hpUiState

    /**
     * Call getHpCharacters() on init so we can display status immediately.
     */
    init {
        getHpCharacters()
    }

    /**
     * Gets characters information from the HP-API Retrofit service
     */
    fun getHpCharacters() {
        viewModelScope.launch {
            _hpUiState.value = HpUiState.Loading
            _hpUiState.value = try {

                //val charactersList = hpCharactersRepository.getHpCharactersFromApi()
                hpCharactersRepository.refreshCharacters()
                //val charactersDB = hpCharactersRepository.getAllHpCharactersStream()
                val characterDb = hpCharactersRepository.getHpCharacterStream(0)
                HpUiState.Success(
                    "SuccessDB: ${characterDb} Harry Potter HpCharacters retrieved: "
                )
//                HpUiState.Success(
//                    "Success: ${charactersList.size} Harry Potter HpCharacters retrieved: " +
//                            "${charactersList[0]}"
//                )

            } catch (e: IOException) {
                HpUiState.Error
            } catch (e: HttpException) {
                HpUiState.Error
            }
        }
    }

    /**
     * Factory for [HpViewModel] that takes [apiHpCharactersRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HarryPotterApplication)
                val hpCharactersRepository = application.container.hpCharactersRepository
                HpViewModel(hpCharactersRepository = hpCharactersRepository)
            }
        }
    }
}