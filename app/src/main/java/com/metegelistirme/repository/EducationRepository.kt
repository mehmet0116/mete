package com.metegelistirme.repository

import com.metegelistirme.database.AppDatabase
import com.metegelistirme.database.entities.ProgressEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EducationRepository @Inject constructor(
    private val database: AppDatabase
) {
    
    // Progress operations
    suspend fun saveProgress(progress: ProgressEntity): Long {
        return withContext(Dispatchers.IO) {
            try {
                database.progressDao().insertProgress(progress)
            } catch (e: Exception) {
                Timber.e(e, "Error saving progress")
                -1L
            }
        }
    }
    
    fun getAllProgress(): Flow<List<ProgressEntity>> {
        return database.progressDao().getAllProgress()
    }
    
    suspend fun getProgressByModule(moduleId: String): ProgressEntity? {
        return withContext(Dispatchers.IO) {
            database.progressDao().getProgressByModule(moduleId)
        }
    }

    suspend fun updateProgress(progress: ProgressEntity) {
        withContext(Dispatchers.IO) {
            database.progressDao().updateProgress(progress)
        }
    }

    suspend fun deleteProgress(moduleId: String) {
        withContext(Dispatchers.IO) {
            database.progressDao().deleteProgress(moduleId)
        }
    }
    
    suspend fun clearAllProgress() {
        withContext(Dispatchers.IO) {
            database.progressDao().deleteAllProgress()
        }
    }
}