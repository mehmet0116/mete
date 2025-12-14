package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.ImageResource
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageResourceDao {
    @Query("SELECT * FROM image_resources WHERE moduleId = :moduleId")
    fun getImagesByModule(moduleId: String): Flow<List<ImageResource>>

    @Query("SELECT * FROM image_resources WHERE id = :id")
    suspend fun getImageById(id: Long): ImageResource?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageResource: ImageResource): Long

    @Update
    suspend fun update(imageResource: ImageResource)

    @Delete
    suspend fun delete(imageResource: ImageResource)
}

