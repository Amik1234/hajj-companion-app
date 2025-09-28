package com.hajjcompanion.core.data.rituals

import com.hajjcompanion.core.data.rituals.db.RitualDao
import com.hajjcompanion.core.data.rituals.db.RitualProgressDao
import com.hajjcompanion.core.data.rituals.db.RitualStepDao
import com.hajjcompanion.core.domain.rituals.Ritual
import com.hajjcompanion.core.domain.rituals.RitualProgress
import com.hajjcompanion.core.domain.rituals.RitualStep
import com.hajjcompanion.core.domain.rituals.RitualsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant

import javax.inject.Inject

class RitualsRepositoryImpl @Inject constructor(
    private val ritualDao: RitualDao,
    private val stepDao: RitualStepDao,
    private val progressDao: RitualProgressDao,
) : RitualsRepository {
    override fun getAllRituals(): Flow<List<Ritual>> =
        ritualDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getRitualById(id: String): Flow<Ritual?> =
        ritualDao.getById(id).map { it?.toDomain() }

    override fun getRitualSteps(ritualId: String): Flow<List<RitualStep>> =
        stepDao.getByRitual(ritualId).map { list -> list.map { it.toDomain() } }

    override suspend fun markStepCompleted(stepId: Int, completed: Boolean) {
        stepDao.setCompleted(stepId, completed)
    }

    override fun getUserProgress(ritualId: String): Flow<RitualProgress> =
        progressDao.get(ritualId).map { entity ->
            val e = entity ?: com.hajjcompanion.core.data.rituals.db.RitualProgressEntity(
                ritualId = ritualId,
                completedStepIdsJson = "[]",
                lastUpdated = Instant.now().toEpochMilli()
            )
            e.toDomain()
        }
}
