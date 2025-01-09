package com.example.harrypottermc.ui.housecharacters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypottermc.R
import com.example.harrypottermc.data.HpCharactersRepository
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

object HouseCharactersDestination : NavigationDestination {
    override val route = "house_characters"
    override val titleRes = R.string.house_characters_title
    const val HOUSE_NAME = "houseName"
    val routeWithArgs = "$route/{$HOUSE_NAME}"
}

class HouseCharactersViewModel(
    savedStateHandle: SavedStateHandle,
    characterRepository: HpCharactersRepository,
) : ViewModel() {

    private val houseName: String = checkNotNull(savedStateHandle[HouseCharactersDestination.HOUSE_NAME])

    val houseCharactersUiState: StateFlow<HouseCharactersUiState> =
        characterRepository.getHpCharacterByHouseNameStream(houseName)
            .map { HouseCharactersUiState(it, houseName) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HouseCharactersUiState(emptyList(), houseName)
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HouseCharactersScreen
 */
data class HouseCharactersUiState(val characters: List<HpCharacter>, val houseName: String)