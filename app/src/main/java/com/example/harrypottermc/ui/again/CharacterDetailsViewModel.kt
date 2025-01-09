package com.example.harrypottermc.ui.again

import androidx.compose.ui.text.capitalize
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harrypottermc.R
import com.example.harrypottermc.data.HpCharactersRepository
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.model.Wand
import com.example.harrypottermc.ui.again.navigation.NavigationDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.util.Locale

object CharacterDetailsDestination : NavigationDestination {
    override val route = "character_details"
    override val titleRes = R.string.character_detail_title
    const val characterId = "characterId"
    val routeWithCharacterId = "$route/{$characterId}"
}

/**
 * ViewModel to retrieve a character from the [CharacterRepository]'s data source.
 */
class CharacterDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    val characterRepository: HpCharactersRepository,
) : ViewModel() {

    private val characterId: String = checkNotNull(savedStateHandle[CharacterDetailsDestination.characterId])

    /**
     * Holds the character details UI state. The data is retrieved from [CharacterRepository] and
     * mapped to the UI state.
     */
    val uiState: StateFlow<CharacterDetailsUiState> =
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

fun CharacterDetails.toCharacter(): HpCharacter {
    return HpCharacter(
        id = this.id,
        name = this.name,
        gender = this.gender.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
        alternateNames = this.altNames,
        species = this.species.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
        house= "",
        dateOfBirth = "dateOfBirth",
        yearOfBirth = 0,
        wizard = true,
        ancestry = "ancestry",
        eyeColour = "eyeColour",
        hairColour = "hairColour",
        wand = Wand("", "", 0.0f),
        patronus = "patronus",
        hogwartsStudent = true,
        hogwartsStaff = true,
        actor = this.actor,
        alternateActors = emptyList(),
        alive = true,
        image = "image",
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
