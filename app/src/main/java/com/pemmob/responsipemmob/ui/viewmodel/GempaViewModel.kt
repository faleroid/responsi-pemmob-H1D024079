package com.pemmob.responsipemmob.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.responsipemmob.data.model.Gempa
import com.pemmob.responsipemmob.data.repository.GempaRepository
import com.pemmob.responsipemmob.ui.state.GempaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GempaViewModel(
    private val repository: GempaRepository = GempaRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<GempaUiState>(GempaUiState.Loading)
    val uiState: StateFlow<GempaUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        loadGempa()
    }

    fun loadGempa() {
        viewModelScope.launch {
            _uiState.value = GempaUiState.Loading

            repository.getGempaTerkini()
                .onSuccess { _uiState.value = GempaUiState.Success(it) }
                .onFailure {
                    _uiState.value = GempaUiState.Error(
                        it.message ?: "Terjadi kesalahan saat mengambil data."
                    )
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun filterGempa(list: List<Gempa>, query: String): List<Gempa> =
        if (query.isBlank()) list
        else list.filter { it.wilayah?.contains(query, ignoreCase = true) == true }

    fun getGempaByIndex(index: Int): Gempa? =
        (uiState.value as? GempaUiState.Success)?.data?.getOrNull(index)
}