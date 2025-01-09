package com.example.harrypottermc.ui.again

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypottermc.data.HpCharactersRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve all items in the Room database.
 */
class HomeViewModel(itemsRepository: HpCharactersRepository) : ViewModel() {
    private val repo = itemsRepository

    val housesNamesUiState: StateFlow<HousesNamesUiState> =
        itemsRepository.getAllHousesNamesStream().map { housesList ->
            HousesNamesUiState(housesList.map { it.ifEmpty { "Unassigned" } })
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HousesNamesUiState()
        )

    fun refresh() {
        viewModelScope.launch { repo.refreshCharacters() }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HousesNamesUiState(val housesNames: List<String> = listOf())
