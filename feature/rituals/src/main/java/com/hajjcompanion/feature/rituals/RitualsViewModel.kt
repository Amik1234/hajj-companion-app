package com.hajjcompanion.feature.rituals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajjcompanion.core.domain.rituals.Ritual
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import com.hajjcompanion.core.domain.rituals.RitualCategory
import com.hajjcompanion.core.domain.rituals.RitualDifficulty
import javax.inject.Inject

@HiltViewModel
class RitualsViewModel @Inject constructor(
    repository: RitualsRepository
) : ViewModel() {
    data class UiState(
        val isLoading: Boolean = true,
        val rituals: List<Ritual> = emptyList(),
        val query: String = "",
        val category: RitualCategory? = null,
        val difficulty: RitualDifficulty? = null
    )

    private val query = MutableStateFlow("")
    private val category = MutableStateFlow<RitualCategory?>(null)
    private val difficulty = MutableStateFlow<RitualDifficulty?>(null)

    val state: StateFlow<UiState> = combine(
        repository.getAllRituals(), query, category, difficulty
    ) { list, q, c, d ->
        val filtered = list.filter { r ->
            (q.isBlank() || r.name.contains(q, ignoreCase = true) || r.fullDescription.contains(q, ignoreCase = true)) &&
            (c == null || r.category == c) &&
            (d == null || r.difficulty == d)
        }
        UiState(isLoading = false, rituals = filtered, query = q, category = c, difficulty = d)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())

    fun setQuery(value: String) { query.value = value }
    fun setCategory(value: RitualCategory?) { category.value = value }
    fun setDifficulty(value: RitualDifficulty?) { difficulty.value = value }
}
