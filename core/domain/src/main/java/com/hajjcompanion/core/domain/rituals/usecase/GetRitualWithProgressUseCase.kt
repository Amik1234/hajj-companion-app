package com.hajjcompanion.core.domain.rituals.usecase

import com.hajjcompanion.core.domain.rituals.RitualWithProgress
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import kotlinx.coroutines.flow.combine

class GetRitualWithProgressUseCase(
    private val repository: RitualsRepository
) {
    operator fun invoke(ritualId: String) =
        combine(
            repository.getRitualById(ritualId),
            repository.getUserProgress(ritualId)
        ) { ritual, progress ->
            RitualWithProgress(ritual, progress)
        }
}
