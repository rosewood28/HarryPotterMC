package com.example.harrypottermc.ui.characterdetails

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harrypottermc.R
import com.example.harrypottermc.ui.AppViewModelProvider

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.characterDetailsUiState.collectAsState()

    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        ItemDetailsBody(
            characterDetailsUiState = uiState,
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun ItemDetailsBody(
    characterDetailsUiState: CharacterDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        ItemDetails(
            item = characterDetailsUiState.characterDetails,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ItemDetails(
    item: CharacterDetails, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            ItemDetailsRow(
                labelResID = R.string.character,
                itemDetail = item.name,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )

            if (item.altNames.isNotEmpty()) {
                ItemDetailsMultiRow(
                    labelResID = R.string.altNames,
                    itemDetail = item.altNames,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                        .heightIn(max = 300.dp)
                )
            }

            if (item.gender.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.gender,
                    itemDetail = item.gender,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.dob.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.dob,
                    itemDetail = item.dob,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.species.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.species,
                    itemDetail = item.species,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.eyes.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.eyes,
                    itemDetail = item.eyes,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.hair.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.hair,
                    itemDetail = item.hair,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.ancestry.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.ancestry,
                    itemDetail = item.ancestry,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.patronus.isNotEmpty()) {
                ItemDetailsRow(
                    labelResID = R.string.patronus,
                    itemDetail = item.patronus,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(
                            id = R.dimen.padding_medium
                        )
                    )
                )
            }

            if (item.actors.isNotEmpty()) {
                ItemDetailsMultiRow(
                    labelResID = R.string.actor,
                    itemDetail = item.actors,
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                        .heightIn(max = 500.dp)
                )
            }
        }
    }

    val wandDet = item.wand

    if (!wandDet.noWand) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues(vertical = dimensionResource(id = R.dimen.padding_small))),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Wand",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
            ) {
                if (wandDet.wood.isNotEmpty()) {
                    ItemDetailsRow(
                        labelResID = R.string.wood,
                        itemDetail = wandDet.wood,
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                    )
                }

                if (wandDet.core.isNotEmpty()) {
                    ItemDetailsRow(
                        labelResID = R.string.core,
                        itemDetail = wandDet.core,
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                    )
                }

                if (wandDet.length.isNotEmpty()) {
                    ItemDetailsRow(
                        labelResID = R.string.length,
                        itemDetail = wandDet.length,
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(
                                id = R.dimen.padding_medium
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ItemDetailsMultiRow(
    @StringRes labelResID: Int, itemDetail: List<String>, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        LazyColumn(
            Modifier.wrapContentWidth(Alignment.End)
        ) {
            items(items = itemDetail, key = { it }) { item ->
                Text(
                    text = item,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



