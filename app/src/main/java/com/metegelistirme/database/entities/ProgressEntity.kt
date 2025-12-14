package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    val moduleId: String,
    val progress: Int,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val childName: String = "",
    val module: String = "",
    val level: Int = 0,
    val score: Int = 0
)
