package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dummy")
data class DummyEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "placeholder"
)
