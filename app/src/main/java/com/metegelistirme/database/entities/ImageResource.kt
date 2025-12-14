package com.metegelistirme.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_resources")
data class ImageResource(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moduleId: String,
    val fileName: String,
    val title: String,
    val description: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val fileSize: Long = 0,
    val localPath: String = "",
    val isDownloaded: Boolean = false
)

