package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.AudioResource
import kotlinx.coroutines.flow.Flow

@Dao
interface AudioResourceDao {
    @Query("SELECT * FROM audio_resources WHERE moduleId = :moduleId")
    fun getAudioByModule(moduleId: String): Flow<List<AudioResource>>

    @Query("SELECT * FROM audio_resources WHERE id = :id")
    suspend fun getAudioById(id: Long): AudioResource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(audioResource: AudioResource): Long

    @Update
    suspend fun update(audioResource: AudioResource)

    @Delete
    suspend fun delete(audioResource: AudioResource)
}

