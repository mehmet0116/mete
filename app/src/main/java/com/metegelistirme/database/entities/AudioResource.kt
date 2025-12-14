package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_resources")
data class AudioResource(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moduleId: String,
    val fileName: String,
    val title: String,
    val description: String = "",
    val duration: Int = 0,
    val fileSize: Long = 0,
    val localPath: String = "",
    val isDownloaded: Boolean = false
)

