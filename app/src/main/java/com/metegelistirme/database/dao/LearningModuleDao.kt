package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.LearningModule
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningModuleDao {
    @Query("SELECT * FROM learning_modules ORDER BY sortOrder ASC")
    fun getAllModules(): Flow<List<LearningModule>>

    @Query("SELECT * FROM learning_modules WHERE id = :moduleId")
    suspend fun getModuleById(moduleId: String): LearningModule?

    @Query("SELECT * FROM learning_modules WHERE category = :category ORDER BY sortOrder ASC")
    fun getModulesByCategory(category: String): Flow<List<LearningModule>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(module: LearningModule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(modules: List<LearningModule>)

    @Update
    suspend fun update(module: LearningModule)

    @Delete
    suspend fun delete(module: LearningModule)
}

