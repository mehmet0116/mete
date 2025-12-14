package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE childProfileId = :childProfileId")
    fun getProgressByChildId(childProfileId: Long): Flow<List<UserProgress>>

    @Query("SELECT * FROM user_progress WHERE childProfileId = :childProfileId AND moduleId = :moduleId")
    suspend fun getProgressByModule(childProfileId: Long, moduleId: String): UserProgress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userProgress: UserProgress): Long

    @Update
    suspend fun update(userProgress: UserProgress)

    @Delete
    suspend fun delete(userProgress: UserProgress)

    @Query("DELETE FROM user_progress WHERE childProfileId = :childProfileId")
    suspend fun deleteAllByChildId(childProfileId: Long)
}

