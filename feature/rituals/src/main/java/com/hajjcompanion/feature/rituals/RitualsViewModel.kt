package com.hajjcompanion.feature.rituals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajjcompanion.core.domain.rituals.Ritual
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RitualsViewModel @Inject constructor(
    repository: RitualsRepository
) : ViewModel() {
    data class UiState(
        val isLoading: Boolean = true,
        val rituals: List<Ritual> = emptyList()
    )

    val state: StateFlow<UiState> = repository.getAllRituals()
        .map { UiState(isLoading = false, rituals = it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())
}
