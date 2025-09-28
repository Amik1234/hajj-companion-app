package com.hajjcompanion.core.data.rituals.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RitualDao {
    @Query("SELECT * FROM rituals ORDER BY name ASC")
    fun getAll(): Flow<List<RitualEntity>>

    @Query("SELECT * FROM rituals WHERE id = :id LIMIT 1")
    fun getById(id: String): Flow<RitualEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<RitualEntity>)
}

@Dao
interface RitualStepDao {
    @Query("SELECT * FROM ritual_steps WHERE ritualId = :ritualId ORDER BY stepNumber ASC")
    fun getByRitual(ritualId: String): Flow<List<RitualStepEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<RitualStepEntity>)

    @Query("UPDATE ritual_steps SET completed = :completed WHERE id = :stepId")
    suspend fun setCompleted(stepId: Int, completed: Boolean)
}

@Dao
interface RitualProgressDao {
    @Query("SELECT * FROM ritual_progress WHERE ritualId = :ritualId LIMIT 1")
    fun get(ritualId: String): Flow<RitualProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entity: RitualProgressEntity)

    @Update
    suspend fun update(entity: RitualProgressEntity)
}
