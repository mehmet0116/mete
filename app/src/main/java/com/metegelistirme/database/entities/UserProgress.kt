package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val childProfileId: Long,
    val moduleId: String,
    val progress: Int = 0,
    val completedLessons: Int = 0,
    val totalLessons: Int = 0,
    val lastAccessDate: Date = Date(),
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
)

