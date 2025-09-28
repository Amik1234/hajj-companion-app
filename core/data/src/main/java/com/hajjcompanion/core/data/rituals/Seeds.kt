package com.hajjcompanion.core.data.rituals

import com.hajjcompanion.core.data.rituals.db.RitualDao
import com.hajjcompanion.core.data.rituals.db.RitualEntity
import com.hajjcompanion.core.data.rituals.db.RitualStepDao
import com.hajjcompanion.core.data.rituals.db.RitualStepEntity
import kotlinx.coroutines.flow.first

object Seeds {
    suspend fun seedIfEmpty(ritualDao: RitualDao, stepDao: RitualStepDao) {
        val existing = ritualDao.getAll().first()
        if (existing.isNotEmpty()) return

        val rituals = listOf(
            RitualEntity(
                id = "umrah_basic",
                name = "Umrah Basic",
                fullDescription = "Basic Umrah steps",
                category = "UMRAH",
                estimatedDurationMinutes = 180,
                difficulty = "BEGINNER",
                prerequisitesJson = "[]",
                audioGuideUrl = null,
                videoUrl = null,
                isOfflineAvailable = true,
                lastUpdated = System.currentTimeMillis()
            ),
            RitualEntity(
                id = "hajj_tamattu",
                name = "Hajj Tamattu",
                fullDescription = "Hajj Tamattu overview",
                category = "HAJJ",
                estimatedDurationMinutes = 7200,
                difficulty = "INTERMEDIATE",
                prerequisitesJson = "[]",
                audioGuideUrl = null,
                videoUrl = null,
                isOfflineAvailable = true,
                lastUpdated = System.currentTimeMillis()
            )
        )
        ritualDao.upsertAll(rituals)

        val steps = listOf(
            RitualStepEntity(
                ritualId = "umrah_basic",
                stepNumber = 1,
                title = "Ihram",
                description = "Enter state of Ihram",
                duaArabic = null,
                duaTransliteration = null,
                duaTranslation = null,
                audioUrl = null,
                estimatedDurationSec = 600,
                gpsCoordinatesJson = null,
                isOptional = false,
                completed = false
            ),
            RitualStepEntity(
                ritualId = "umrah_basic",
                stepNumber = 2,
                title = "Tawaf",
                description = "Perform Tawaf around Kaaba",
                duaArabic = null,
                duaTransliteration = null,
                duaTranslation = null,
                audioUrl = null,
                estimatedDurationSec = 3600,
                gpsCoordinatesJson = null,
                isOptional = false,
                completed = false
            )
        )
        stepDao.upsertAll(steps)
    }
}
