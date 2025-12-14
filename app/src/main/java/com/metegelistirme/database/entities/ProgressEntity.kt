package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val childName: String,
    val module: String,
    val level: Int,
    val score: Int,
    val timestamp: Long
)
