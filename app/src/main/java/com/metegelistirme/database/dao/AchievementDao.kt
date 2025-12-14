package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.Achievement
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements WHERE childProfileId = :childProfileId ORDER BY unlockedAt DESC")
    fun getAchievementsByChildId(childProfileId: Long): Flow<List<Achievement>>

    @Query("SELECT * FROM achievements WHERE childProfileId = :childProfileId AND isUnlocked = 1")
    fun getUnlockedAchievements(childProfileId: Long): Flow<List<Achievement>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(achievement: Achievement): Long

    @Update
    suspend fun update(achievement: Achievement)

    @Query("DELETE FROM achievements WHERE childProfileId = :childProfileId")
    suspend fun deleteAllByChildId(childProfileId: Long)
}

