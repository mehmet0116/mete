package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.ChildProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildProfileDao {
    @Query("SELECT * FROM child_profiles ORDER BY createdAt DESC")
    fun getAllProfiles(): Flow<List<ChildProfile>>

    @Query("SELECT * FROM child_profiles WHERE id = :profileId")
    suspend fun getProfileById(profileId: Long): ChildProfile?

    @Query("SELECT * FROM child_profiles WHERE isActive = 1 LIMIT 1")
    suspend fun getActiveProfile(): ChildProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(childProfile: ChildProfile): Long

    @Update
    suspend fun update(childProfile: ChildProfile)

    @Delete
    suspend fun delete(childProfile: ChildProfile)

    @Query("UPDATE child_profiles SET isActive = 0")
    suspend fun deactivateAll()

    @Query("UPDATE child_profiles SET isActive = 1 WHERE id = :profileId")
    suspend fun setActiveProfile(profileId: Long)
}

