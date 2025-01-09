package com.example.harrypottermc.ui.again

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harrypottermc.R
import com.example.harrypottermc.ui.again.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

/**
 * Entry route for Home screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToCharactersBelongingToHouse: (String) -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier,
) {

    val housesNamesUiState by homeViewModel.housesNamesUiState.collectAsState()

    Scaffold { innerPadding ->
        HomeBody(
            housesNames = housesNamesUiState.housesNames,
            onItemClick = navigateToCharactersBelongingToHouse,
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .height(50.dp),
            contentPadding = innerPadding,
            refreshFunc = { homeViewModel.refresh() }
        )
    }
}

@Composable
private fun HomeBody(
    housesNames: List<String>,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    refreshFunc: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = "Harry Potter\nUniverse",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(PaddingValues(vertical = dimensionResource(id = R.dimen.padding_extra_large)))
        )

        if (housesNames.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_DB),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.plus(TextStyle(fontSize = 32.sp)),
                modifier = Modifier.padding(PaddingValues(vertical = dimensionResource(id = R.dimen.padding_extra_large))),
            )
            Button(onClick = {
                refreshFunc()
            }) {
                Text(
                    text = "Tap to Refresh"
                )
            }
        } else {
            Text(
                text = "Please select a house to see its assigned characters",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            HousesList(
                housesList = housesNames,
                onItemClick = { onItemClick(it) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun HousesList(
    housesList: List<String>,
    onItemClick: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = housesList, key = { it }) { item ->
            HouseItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }
    }
}

@Composable
private fun HouseItem(
    item: String, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}
