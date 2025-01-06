package com.example.harrypottermc.ui.screns

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.harrypottermc.data.HpCharactersRepository
import com.example.harrypottermc.model.HpCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterListViewModel (
    private val hpCharactersRepository: HpCharactersRepository
): ViewModel() {

    fun getCharactersByHouseFromDB(house: String): List<Character> {
        return when (house) {
            "Gryffindor" -> listOf(Character("Harry Potter"), Character("Hermione Granger"))
            "Hufflepuff" -> listOf(Character("Cedric Diggory"))
            "Ravenclaw" -> listOf(Character("Luna Lovegood"))
            "Slytherin" -> listOf(Character("Draco Malfoy"))
            else -> emptyList()
        }
    }

}

//data class CharacterUiState(
//    val characterDetalis: CharacterDetails = CharacterDetails(),
//    val isEntryValid: Boolean = false
//)



