package com.example.harrypottermc.ui.screns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HouseSelectionScreen(onHouseSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Harry Potter Universe",
            //style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        val houses = listOf("Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin")
        houses.forEach { house ->
            Button(
                onClick = { onHouseSelected(house) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = house)
            }
        }
    }
}