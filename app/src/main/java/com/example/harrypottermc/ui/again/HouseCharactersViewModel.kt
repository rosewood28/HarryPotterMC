package com.example.harrypottermc.ui.again

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypottermc.R
import com.example.harrypottermc.data.HpCharactersRepository
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.ui.again.navigation.NavigationDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

object HouseCharactersDestination : NavigationDestination {
    override val route = "house_characters"
    override val titleRes = R.string.house_characters_title
    const val houseName = "houseName"
    val routeWithArgs = "$route/{$houseName}"
}

class HouseCharactersViewModel(
    savedStateHandle: SavedStateHandle,
    val characterRepository: HpCharactersRepository,
) : ViewModel() {

    private val houseName: String = checkNotNull(savedStateHandle[HouseCharactersDestination.houseName])

    val houseCharactersUiState: StateFlow<HouseCharactersUiState> =
        characterRepository.getHpCharacterByHouseNameStream(houseName)
            .map { HouseCharactersUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HouseCharactersUiState(emptyList())
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class HouseCharactersUiState(val characters: List<HpCharacter>)