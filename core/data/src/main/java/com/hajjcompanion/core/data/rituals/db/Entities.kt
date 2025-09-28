package com.hajjcompanion.core.data.rituals.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rituals")
data class RitualEntity(
    @PrimaryKey val id: String,
    val name: String,
    val fullDescription: String,
    val category: String,
    val estimatedDurationMinutes: Int,
    val difficulty: String,
    val prerequisitesJson: String,
    val audioGuideUrl: String?,
    val videoUrl: String?,
    val isOfflineAvailable: Boolean,
    val lastUpdated: Long
)

@Entity(
    tableName = "ritual_steps",
    foreignKeys = [
        ForeignKey(
            entity = RitualEntity::class,
            parentColumns = ["id"],
            childColumns = ["ritualId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ritualId"), Index(value = ["ritualId", "stepNumber"], unique = true)]
)
data class RitualStepEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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
    val isOptional: Boolean,
    val completed: Boolean
)

@Entity(
    tableName = "ritual_progress",
    foreignKeys = [
        ForeignKey(
            entity = RitualEntity::class,
            parentColumns = ["id"],
            childColumns = ["ritualId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("ritualId", unique = true)]
)
data class RitualProgressEntity(
    @PrimaryKey val ritualId: String,
    val completedStepIdsJson: String,
    val lastUpdated: Long
)
