package com.example.harrypottermc.ui.characterdetails

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
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
    val actors: List<String> = if (!this.actor.isNullOrEmpty())
        listOf(this.actor).plus(this.alternateActors)
    else
        emptyList()

    val ancestry: String = if (this.ancestry.isNullOrEmpty() && this.wizard == true)
        "Wizard"
    else if (this.wizard == false) {
        this.ancestry.toString().capitalize(Locale.current)
    } else {
        this.ancestry.toString().capitalize(Locale.current).plus(" Wizard")
    }

    val hpWand = this.wand
    val wand: WandDetails = if (hpWand == null || (hpWand.wood.isNullOrEmpty() && hpWand.core.isNullOrEmpty() && hpWand.length == null))
        WandDetails()
    else
        WandDetails(
            noWand = false,
            core = hpWand.core.toString().capitalize(Locale.current),
            wood = hpWand.wood.toString().capitalize(Locale.current),
            length = hpWand.length?.let {
                if (it % 1 == 0.0f) {
                    "${it.toInt()} in"
                } else {
                    "$it in"
                }
            } ?: ""
        )

    return CharacterDetails(
        id = this.id,
        name = this.name.toString(),
        gender = this.gender.toString().capitalize(Locale.current),
        altNames = this.alternateNames,
        actors = actors,
        species = this.species.toString().capitalize(Locale.current),
        ancestry = ancestry,
        dob = this.dateOfBirth?.let {this.dateOfBirth} ?: "",
        eyes = this.eyeColour.toString().capitalize(Locale.current),
        hair = this.hairColour.toString().capitalize(Locale.current),
        patronus = this.patronus.toString().capitalize(Locale.current),
        wand = wand
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
    val actors: List<String> = emptyList(),
    val species: String = "",
    val ancestry: String = "",
    val dob: String = "",
    val eyes: String = "",
    val hair: String = "",
    val patronus: String = "",
    val wand: WandDetails = WandDetails()
)


/**
 * Data class for wands to be displayed in the UI.
 */
data class WandDetails(
    val noWand: Boolean = true,
    val wood: String = "",
    val core: String = "",
    val length: String = "",
)