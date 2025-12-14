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
    suspend fun insert(progress: ProgressEntity): Long

    @Query("SELECT * FROM progress ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ProgressEntity>>

    @Query("DELETE FROM progress")
    suspend fun clearAll()
}
