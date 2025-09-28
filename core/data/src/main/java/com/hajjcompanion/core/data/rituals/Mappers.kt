package com.hajjcompanion.core.data.rituals

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hajjcompanion.core.data.rituals.db.RitualEntity
import com.hajjcompanion.core.data.rituals.db.RitualProgressEntity
import com.hajjcompanion.core.data.rituals.db.RitualStepEntity
import com.hajjcompanion.core.domain.rituals.*

private val gson = Gson()

private val listStringType = object : TypeToken<List<String>>() {}.type
private val setIntType = object : TypeToken<Set<Int>>() {}.type

fun RitualEntity.toDomain(): Ritual = Ritual(
    id = id,
    name = name,
    fullDescription = fullDescription,
    category = RitualCategory.valueOf(category),
    estimatedDurationMinutes = estimatedDurationMinutes,
    difficulty = RitualDifficulty.valueOf(difficulty),
    prerequisites = gson.fromJson(prerequisitesJson, listStringType),
    audioGuideUrl = audioGuideUrl,
    videoUrl = videoUrl,
    isOfflineAvailable = isOfflineAvailable,
    lastUpdated = lastUpdated
)

fun Ritual.toEntity(): RitualEntity = RitualEntity(
    id = id,
    name = name,
    fullDescription = fullDescription,
    category = category.name,
    estimatedDurationMinutes = estimatedDurationMinutes,
    difficulty = difficulty.name,
    prerequisitesJson = gson.toJson(prerequisites),
    audioGuideUrl = audioGuideUrl,
    videoUrl = videoUrl,
    isOfflineAvailable = isOfflineAvailable,
    lastUpdated = lastUpdated
)

fun RitualStepEntity.toDomain(): RitualStep = RitualStep(
    id = id,
    ritualId = ritualId,
    stepNumber = stepNumber,
    title = title,
    description = description,
    duaArabic = duaArabic,
    duaTransliteration = duaTransliteration,
    duaTranslation = duaTranslation,
    audioUrl = audioUrl,
    estimatedDurationSec = estimatedDurationSec,
    gpsCoordinatesJson = gpsCoordinatesJson,
    isOptional = isOptional,
    completed = completed
)

fun RitualProgressEntity.toDomain(): RitualProgress = RitualProgress(
    ritualId = ritualId,
    completedStepIds = gson.fromJson(completedStepIdsJson, setIntType),
    lastUpdated = lastUpdated
)

fun RitualProgress.toEntity(): RitualProgressEntity = RitualProgressEntity(
    ritualId = ritualId,
    completedStepIdsJson = gson.toJson(completedStepIds),
    lastUpdated = lastUpdated
)
