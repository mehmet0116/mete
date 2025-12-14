package com.metegelistirme.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metegelistirme.database.entities.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProgress(progress: ProgressEntity): Long

    @Query("SELECT * FROM progress ORDER BY timestamp DESC")
    fun getAllProgress(): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE moduleId = :moduleId LIMIT 1")
    suspend fun getProgressByModule(moduleId: String): ProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProgress(progress: ProgressEntity)

    @Query("DELETE FROM progress WHERE moduleId = :moduleId")
    suspend fun deleteProgress(moduleId: String)

    @Query("DELETE FROM progress")
    suspend fun deleteAllProgress()
}
