package com.example.harrypottermc.ui.screns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CharacterListScreen(
    house: String,
    onCharacterSelected: (String) -> Unit,
    //viewModel: CharacterListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = house,
            //style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
        val characters = getCharactersByHouse(house)
        LazyColumn {
            items(characters) { character ->
                Button(
                    onClick = { onCharacterSelected(character.name) },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    Text(text = character.name)
                }
            }
        }
    }
}

// Mock Data
fun getCharactersByHouse(house: String): List<Character> {
    return when (house) {
        "Gryffindor" -> listOf(Character("Harry Potter"), Character("Hermione Granger"))
        "Hufflepuff" -> listOf(Character("Cedric Diggory"))
        "Ravenclaw" -> listOf(Character("Luna Lovegood"))
        "Slytherin" -> listOf(Character("Draco Malfoy"))
        else -> emptyList()
    }
}

//fun getCharactersByHouseFromDB(house: String): List<Character> {
//    return when (house) {
//        "Gryffindor" -> listOf(Character("Harry Potter"), Character("Hermione Granger"))
//        "Hufflepuff" -> listOf(Character("Cedric Diggory"))
//        "Ravenclaw" -> listOf(Character("Luna Lovegood"))
//        "Slytherin" -> listOf(Character("Draco Malfoy"))
//        else -> emptyList()
//    }
//}

// Data Model
data class Character(val name: String)