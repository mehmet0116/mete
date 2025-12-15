package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String,
    val moduleId: String,
    val activityId: String,
    val progressPercentage: Int,
    val starsEarned: Int,
    val timeSpent: Long, // in milliseconds
    val completedAt: Date?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)