package com.metegelistirme.database.dao

import androidx.room.*
import com.metegelistirme.database.entities.GameScore
import kotlinx.coroutines.flow.Flow

@Dao
interface GameScoreDao {
    @Query("SELECT * FROM game_scores WHERE childProfileId = :childProfileId ORDER BY completedAt DESC")
    fun getScoresByChildId(childProfileId: Long): Flow<List<GameScore>>

    @Query("SELECT * FROM game_scores WHERE childProfileId = :childProfileId AND gameType = :gameType ORDER BY score DESC LIMIT 1")
    suspend fun getHighScore(childProfileId: Long, gameType: String): GameScore?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameScore: GameScore): Long

    @Query("DELETE FROM game_scores WHERE childProfileId = :childProfileId")
    suspend fun deleteAllByChildId(childProfileId: Long)
}

