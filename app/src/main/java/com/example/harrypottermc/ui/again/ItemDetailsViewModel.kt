package com.example.harrypottermc.ui.again

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

object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

/**
 * ViewModel to retrieve a character from the [CharacterRepository]'s data source.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    val characterRepository: HpCharactersRepository,
) : ViewModel() {

    private val characterId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    /**
     * Holds the character details UI state. The data is retrieved from [CharacterRepository] and
     * mapped to the UI state.
     */
    val uiState: StateFlow<ItemDetailsUiState> =
        characterRepository.getHpCharacterStream(characterId)
            .filterNotNull()
            .map { character ->
                ItemDetailsUiState(itemDetails = character.toCharacterDetails())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for CharacterDetailsScreen
 */
data class ItemDetailsUiState(
    val itemDetails: CharacterDetails = CharacterDetails()
)

/**
 * Extension function to convert [Character] to [CharacterDetails].
 */
fun HpCharacter.toCharacterDetails(): CharacterDetails {
    return CharacterDetails(
        id = this.id.toInt(),
        name = this.name.toString(),
        gender = this.gender.toString()
    )
}

fun CharacterDetails.toCharacter(): HpCharacter {
    return HpCharacter(
        id = this.id.toString(),
        name = this.name,
        gender = this.gender,
        alternateNames = emptyList(),
        species = "",
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
        actor = "actor",
        alternateActors = emptyList(),
        alive = true,
        image = "image",
    )
}

/**
 * Data class for character details to be displayed in the UI.
 */
data class CharacterDetails(
    val id: Int = 0,
    val name: String = "",
    val gender: String = ""
)

///**
// * Extension function to convert [HpCharacterUiState] to [HpCharacter]. If the value of [HpCharacterDetails.price] is
// * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
// * [HpCharacterUiState] is not a valid [Int], then the quantity will be set to 0
// */
//fun CharacterDetails.toHpCharacter(): HpCharacter = HpCharacter(
//    id = id.toString(),
//    name = name,
//    alternateNames = emptyList(),
//    species = "",
//    gender = gender,
//    house= "",
//    dateOfBirth = "dateOfBirth",
//    yearOfBirth = 0,
//    wizard = true,
//    ancestry = "ancestry",
//    eyeColour = "eyeColour",
//    hairColour = "hairColour",
//    wand = Wand("", "", 0.0f),
//    patronus = "patronus",
//    hogwartsStudent = true,
//    hogwartsStaff = true,
//    actor = "actor",
//    alternateActors = emptyList(),
//    alive = true,
//    image = "image",
//)

///**
// * Extension function to convert [HpCharacterUiState] to [HpCharacter]. If the value of [HpCharacterDetails.price] is
// * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
// * [HpCharacterUiState] is not a valid [Int], then the quantity will be set to 0
// */
//fun CharacterDetails.toHpCharacter(): HpCharacter = HpCharacter(
//    id = id.toString(),
//    name = name,
//    alternateNames = alternateNames,
//    species = species,
//    gender = gender,
//    house= house,
//    dateOfBirth = dateOfBirth,
//    yearOfBirth = yearOfBirth,
//    wizard = wizard,
//    ancestry = ancestry,
//    eyeColour = eyeColour,
//    hairColour = hairColour,
//    wand = wand,
//    patronus = patronus,
//    hogwartsStudent = hogwartsStudent,
//    hogwartsStaff = hogwartsStaff,
//    actor = actor,
//    alternateActors = alternateActors,
//    alive = alive,
//    image = image,
//)

//data class HpCharacterDetails(
//    val id: String = "",
//    val name: String = "",
//    val alternateNames: List<String> = emptyList(),
//    val species: String = "",
//    val gender: String = "",
//    val house: String = "",
//    val dateOfBirth: String = "",
//    val yearOfBirth: Int = 0,
//    val wizard: Boolean = false,
//    val ancestry: String = "",
//    val eyeColour: String = "",
//    val hairColour: String = "",
//    val wand: Wand = Wand("", "", 0.0f),
//    val patronus: String = "",
//    val hogwartsStudent: Boolean = false,
//    val hogwartsStaff: Boolean = false,
//    val actor: String = "",
//    val alternateActors: List<String> = emptyList(),
//    val alive: Boolean = false,
//    val image: String = ""
//)