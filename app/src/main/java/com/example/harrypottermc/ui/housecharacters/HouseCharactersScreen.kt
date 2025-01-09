package com.example.harrypottermc.ui.housecharacters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harrypottermc.R
import com.example.harrypottermc.model.HpCharacter
import com.example.harrypottermc.ui.AppViewModelProvider

@Composable
fun HouseCharactersScreen(
    navigateToCharacterDetails: (String) -> Unit,
    houseCharactersViewModel: HouseCharactersViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val houseCharactersUiState by houseCharactersViewModel.houseCharactersUiState.collectAsState()

    Scaffold { innerPadding ->
        if (houseCharactersUiState.characters.isEmpty()) {
            Text(
                text = "No characters available for this house.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            HouseCharactersBody(
                houseName = houseCharactersUiState.houseName,
                characters = houseCharactersUiState.characters,
                onItemClick = navigateToCharacterDetails,
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .height(50.dp),
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
private fun HouseCharactersBody(
    houseName: String,
    characters: List<HpCharacter>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = houseName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(PaddingValues(top = dimensionResource(id = R.dimen.titlePad), bottom = dimensionResource(id = R.dimen.padding_large)))
        )

        CharacterList(
            characters = characters,
            onItemClick = { onItemClick(it.id) },
            contentPadding = contentPadding,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
        )
    }
}

@Composable
private fun CharacterList(
    characters: List<HpCharacter>,
    onItemClick: (HpCharacter) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = characters, key = { it.id }) { item ->
            CharacterItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    item: HpCharacter, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.name.toString(),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.gender.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}