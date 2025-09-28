package com.hajjcompanion.core.domain.rituals

import kotlinx.coroutines.flow.Flow
import java.time.Instant

enum class RitualCategory { HAJJ, UMRAH, DAILY_PRAYERS, GENERAL }
enum class RitualDifficulty { BEGINNER, INTERMEDIATE, ADVANCED }

data class Ritual(
    val id: String,
    val name: String,
    val fullDescription: String,
    val category: RitualCategory,
    val estimatedDurationMinutes: Int,
    val difficulty: RitualDifficulty,
    val prerequisites: List<String>,
    val audioGuideUrl: String?,
    val videoUrl: String?,
    val isOfflineAvailable: Boolean = true,
    val lastUpdated: Long = Instant.now().toEpochMilli()
)

data class RitualStep(
    val id: Int,
    val ritualId: String,
    val stepNumber: Int,
    val title: String,
    val description: String,
    val duaArabic: String?,
    val duaTransliteration: String?,
    val duaTranslation: String?,
    val audioUrl: String?,
    val estimatedDurationSec: Int?,
    val gpsCoordinatesJson: String?,
    val isOptional: Boolean = false,
    val completed: Boolean = false
)

data class RitualProgress(
    val ritualId: String,
    val completedStepIds: Set<Int>,
    val lastUpdated: Long
)

data class RitualWithProgress(
    val ritual: Ritual?,
    val progress: RitualProgress
)

interface RitualsRepository {
    fun getAllRituals(): Flow<List<Ritual>>
    fun getRitualById(id: String): Flow<Ritual?>
    fun getRitualSteps(ritualId: String): Flow<List<RitualStep>>
    suspend fun markStepCompleted(stepId: Int, completed: Boolean)
    fun getUserProgress(ritualId: String): Flow<RitualProgress>
}
