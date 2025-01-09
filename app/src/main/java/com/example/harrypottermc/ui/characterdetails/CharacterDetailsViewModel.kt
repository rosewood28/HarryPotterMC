package com.example.harrypottermc.ui.characterdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypottermc.R
import com.example.harrypottermc.data.HpCharactersRepository
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.ui.navigation.NavigationDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

object CharacterDetailsDestination : NavigationDestination {
    override val route = "character_details"
    override val titleRes = R.string.character_detail_title
    const val CHARACTER_ID = "characterId"
    val routeWithCharacterId = "$route/{$CHARACTER_ID}"
}

/**
 * ViewModel to retrieve a character from the [HpCharactersRepository]'s data source.
 */
class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    characterRepository: HpCharactersRepository,
) : ViewModel() {

    private val characterId: String = checkNotNull(savedStateHandle[CharacterDetailsDestination.CHARACTER_ID])

    /**
     * Holds the character details UI state. The data is retrieved from [HpCharactersRepository] and
     * mapped to the UI state.
     */
    val characterDetailsUiState: StateFlow<CharacterDetailsUiState> =
        characterRepository.getHpCharacterStream(characterId)
            .filterNotNull()
            .map { character ->
                CharacterDetailsUiState(characterDetails = character.toCharacterDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = CharacterDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for CharacterDetailsScreen
 */
data class CharacterDetailsUiState(
    val characterDetails: CharacterDetails = CharacterDetails()
)

/**
 * Extension function to convert [Character] to [CharacterDetails].
 */
fun HpCharacter.toCharacterDetails(): CharacterDetails {
    return CharacterDetails(
        id = this.id,
        name = this.name.toString(),
        gender = this.gender.toString(),
        altNames = this.alternateNames,
        actor = this.actor.toString(),
        species = this.species.toString()
    )
}

/**
 * Data class for character details to be displayed in the UI.
 */
data class CharacterDetails(
    val id: String = "",
    val name: String = "",
    val gender: String = "",
    val altNames: List<String> = emptyList(),
    val actor: String = "",
    val species: String = ""
)
