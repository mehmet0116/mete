package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(progress: UserProgress): Long
    
    @Update
    suspend fun update(progress: UserProgress)
    
    @Delete
    suspend fun delete(progress: UserProgress)
    
    @Query("SELECT * FROM user_progress WHERE userId = :userId ORDER BY updatedAt DESC")
    fun getProgressByUser(userId: String): Flow<List<UserProgress>>
    
    @Query("SELECT * FROM user_progress WHERE userId = :userId AND moduleId = :moduleId")
    suspend fun getProgressByModule(userId: String, moduleId: String): UserProgress?
    
    @Query("SELECT AVG(progressPercentage) FROM user_progress WHERE userId = :userId")
    fun getAverageProgress(userId: String): Flow<Float>
    
    @Query("SELECT SUM(timeSpent) FROM user_progress WHERE userId = :userId")
    fun getTotalTimeSpent(userId: String): Flow<Long>
    
    @Query("SELECT SUM(starsEarned) FROM user_progress WHERE userId = :userId")
    fun getTotalStars(userId: String): Flow<Int>
    
    @Query("DELETE FROM user_progress WHERE userId = :userId")
    suspend fun clearUserProgress(userId: String)
}