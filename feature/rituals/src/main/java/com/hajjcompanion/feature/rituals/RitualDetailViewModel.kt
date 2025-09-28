package com.hajjcompanion.feature.rituals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hajjcompanion.core.domain.rituals.RitualStep
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RitualDetailViewModel @Inject constructor(
    private val repository: RitualsRepository
) : ViewModel() {

    data class UiState(
        val title: String = "",
        val steps: List<RitualStep> = emptyList(),
        val completedCount: Int = 0,
        val total: Int = 0
    )

    fun state(ritualId: String): StateFlow<UiState> = repository.getRitualSteps(ritualId)
        .map { steps ->
            val completed = steps.count { it.completed }
            UiState(
                title = ritualId,
                steps = steps,
                completedCount = completed,
                total = steps.size
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())

    fun setStepCompleted(stepId: Int, completed: Boolean) {
        viewModelScope.launch { repository.markStepCompleted(stepId, completed) }
    }
}
