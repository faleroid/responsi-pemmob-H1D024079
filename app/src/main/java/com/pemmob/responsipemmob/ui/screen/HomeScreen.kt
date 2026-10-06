package com.pemmob.responsipemmob.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pemmob.responsipemmob.ui.components.ErrorView
import com.pemmob.responsipemmob.ui.components.GempaItem
import com.pemmob.responsipemmob.ui.components.GempaSearchBar
import com.pemmob.responsipemmob.ui.components.LoadingView
import com.pemmob.responsipemmob.ui.state.GempaUiState
import com.pemmob.responsipemmob.ui.viewmodel.GempaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: GempaViewModel,
    onGempaClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Monitoring Gempa Bumi") },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            GempaSearchBar(
                query = query,
                onQueryChange = viewModel::onSearchQueryChange
            )

            when (val state = uiState) {
                is GempaUiState.Loading -> LoadingView()

                is GempaUiState.Error -> ErrorView(
                    message = state.message,
                    onRetry = viewModel::loadGempa
                )

                is GempaUiState.Success -> {
                    val hasil = viewModel.filterGempa(state.data, query)

                    if (hasil.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Tidak ada data yang ditemukan",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(hasil) { gempa ->
                                GempaItem(
                                    gempa = gempa,
                                    onClick = { onGempaClick(state.data.indexOf(gempa)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}