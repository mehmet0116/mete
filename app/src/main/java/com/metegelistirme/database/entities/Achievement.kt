package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val childProfileId: Long,
    val achievementType: String,
    val title: String,
    val description: String,
    val iconResId: Int = 0,
    val points: Int = 0,
    val isUnlocked: Boolean = false,
    val unlockedAt: Date? = null
)

