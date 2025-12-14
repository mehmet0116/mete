package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "child_profiles")
data class ChildProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val age: Int,
    val avatarResId: Int = 0,
    val totalPoints: Int = 0,
    val level: Int = 1,
    val createdAt: Date = Date(),
    val isActive: Boolean = true
)

