package com.hajjcompanion.core.data.rituals.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        RitualEntity::class,
        RitualStepEntity::class,
        RitualProgressEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ritualDao(): RitualDao
    abstract fun ritualStepDao(): RitualStepDao
    abstract fun ritualProgressDao(): RitualProgressDao
}
