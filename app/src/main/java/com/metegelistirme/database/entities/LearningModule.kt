package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "learning_modules")
data class LearningModule(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val difficulty: String = "beginner",
    val estimatedDuration: Int = 0,
    val iconResId: Int = 0,
    val isLocked: Boolean = false,
    val sortOrder: Int = 0
)

