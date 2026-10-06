package com.pemmob.responsipemmob.ui.state

import com.pemmob.responsipemmob.data.model.Gempa

sealed interface GempaUiState {
    data object Loading : GempaUiState
    data class Success(val data: List<Gempa>) : GempaUiState
    data class Error(val message: String) : GempaUiState
}