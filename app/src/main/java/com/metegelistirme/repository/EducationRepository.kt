package com.metegelistirme.repository

import com.metegelistirme.database.AppDatabase
import com.metegelistirme.database.dao.*
import com.metegelistirme.database.entities.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class EducationRepository @Inject constructor(
    private val database: AppDatabase
) {
    
    // User Progress Operations
    suspend fun saveUserProgress(progress: UserProgress): Long {
        return withContext(Dispatchers.IO) {
            try {
                database.userProgressDao().insert(progress)
            } catch (e: Exception) {
                Timber.e(e, "Error saving user progress")
                -1L
            }
        }
    }
    
    fun getUserProgress(userId: String): Flow<UserProgress?> {
        return database.userProgressDao().getByUserId(userId)
            .flowOn(Dispatchers.IO)
            .map { progress ->
                progress?.apply {
                    // Calculate completion percentage
                    completionPercentage = calculateCompletion()
                }
            }
    }
    
    // Game Scores Operations
    suspend fun saveGameScore(score: GameScore): Long {
        return withContext(Dispatchers.IO) {
            try {
                database.gameScoreDao().insert(score)
            } catch (e: Exception) {
                Timber.e(e, "Error saving game score")
                -1L
            }
        }
    }
    
    fun getHighScores(gameType: String, limit: Int = 10): Flow<List<GameScore>> {
        return database.gameScoreDao().getHighScores(gameType, limit)
            .flowOn(Dispatchers.IO)
    }
    
    // Learning Modules Operations
    fun getAllLearningModules(): Flow<List<LearningModule>> {
        return database.learningModuleDao().getAll()
            .flowOn(Dispatchers.IO)
            .map { modules ->
                modules.sortedBy { it.order }
            }
    }
    
    suspend fun updateModuleProgress(moduleId: String, progress: Int) {
        withContext(Dispatchers.IO) {
            database.learningModuleDao().updateProgress(moduleId, progress)
        }
    }
    
    // Audio Resources Operations
    fun getAudioResource(resourceId: String): Flow<AudioResource?> {
        return database.audioResourceDao().getById(resourceId)
            .flowOn(Dispatchers.IO)
    }
    
    suspend fun preloadAudioResources(moduleId: String) {
        withContext(Dispatchers.IO) {
            // Preload audio resources for a module to reduce loading time
            val resources = database.audioResourceDao().getByModuleId(moduleId)
            Timber.d("Preloaded ${resources.size} audio resources for module $moduleId")
        }
    }
    
    // Child Profiles Operations
    suspend fun saveChildProfile(profile: ChildProfile): Long {
        return withContext(Dispatchers.IO) {
            try {
                database.childProfileDao().insert(profile)
            } catch (e: Exception) {
                Timber.e(e, "Error saving child profile")
                -1L
            }
        }
    }
    
    fun getAllChildProfiles(): Flow<List<ChildProfile>> {
        return database.childProfileDao().getAll()
            .flowOn(Dispatchers.IO)
    }
    
    // Achievements Operations
    suspend fun unlockAchievement(achievementId: String, userId: String) {
        withContext(Dispatchers.IO) {
            database.achievementDao().unlock(achievementId, userId)
        }
    }
    
    fun getUserAchievements(userId: String): Flow<List<Achievement>> {
        return database.achievementDao().getByUserId(userId)
            .flowOn(Dispatchers.IO)
    }
    
    // Batch Operations for Performance
    suspend fun saveMultipleGameScores(scores: List<GameScore>) {
        withContext(Dispatchers.IO) {
            database.runInTransaction {
                scores.forEach { score ->
                    database.gameScoreDao().insert(score)
                }
            }
        }
    }
    
    // Cache Management
    suspend fun clearOldData(days: Int = 30) {
        withContext(Dispatchers.IO) {
            database.gameScoreDao().deleteOlderThan(days)
            Timber.d("Cleared data older than $days days")
        }
    }
}