package com.example.harrypottermc.ui.screns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterDetailsScreen(characterName: String) {
    val character = getCharacterDetails(characterName)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Character Details",
            //style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
        Text(text = "Name: ${character.name}")//, style = MaterialTheme.typography.body1)
        Text(text = "Gender: ${character.gender}")//, style = MaterialTheme.typography.body1)
        Text(text = "House: ${character.house}")//, style = MaterialTheme.typography.body1)
    }
}

// Mock Data
fun getCharacterDetails(name: String): CharacterDetails {
    return when (name) {
        "Harry Potter" -> CharacterDetails("Harry Potter", "Male", "Gryffindor")
        "Hermione Granger" -> CharacterDetails("Hermione Granger", "Female", "Gryffindor")
        "Draco Malfoy" -> CharacterDetails("Draco Malfoy", "Male", "Slytherin")
        else -> CharacterDetails(name, "Unknown", "Unknown")
    }
}

// Data Model
data class CharacterDetails(val name: String, val gender: String, val house: String)