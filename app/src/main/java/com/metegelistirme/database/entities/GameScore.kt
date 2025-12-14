package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "game_scores")
data class GameScore(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val childProfileId: Long,
    val gameType: String,
    val score: Int,
    val maxScore: Int,
    val timeTaken: Long = 0,
    val difficulty: String = "easy",
    val completedAt: Date = Date()
)


