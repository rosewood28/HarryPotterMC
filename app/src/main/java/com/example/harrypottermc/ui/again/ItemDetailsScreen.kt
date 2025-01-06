package com.example.harrypottermc.ui.again

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harrypottermc.R
import com.example.harrypottermc.model.HpCharacter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
//        topBar = {
//            InventoryTopAppBar(
//                title = stringResource(ItemDetailsDestination.titleRes),
//                canNavigateBack = true,
//                navigateUp = navigateBack
//            )
//        },
        modifier = modifier,
    ) { innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = uiState.value,
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
    itemDetailsUiState: ItemDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        ItemDetails(
            item = itemDetailsUiState.itemDetails.toCharacter(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ItemDetails(
    item: HpCharacter, modifier: Modifier = Modifier
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
                itemDetail = item.name.toString(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
            ItemDetailsRow(
                labelResID = R.string.quantity_in_stock,
                itemDetail = item.gender.toString(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_medium
                    )
                )
            )
//            ItemDetailsRow(
//                labelResID = R.string.price,
//                itemDetail = item.,
//                modifier = Modifier.padding(
//                    horizontal = dimensionResource(
//                        id = R.dimen.padding_medium
//                    )
//                )
//            )
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



