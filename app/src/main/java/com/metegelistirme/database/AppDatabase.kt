package com.metegelistirme.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.metegelistirme.database.converters.DateConverter
import com.metegelistirme.database.dao.*
import com.metegelistirme.database.entities.*

@Database(
    entities = [
        UserProgress::class,
        GameSession::class,
        Achievement::class,
        ContentItem::class,
        Question::class,
        Answer::class,
        SoundItem::class,
        ImageResource::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun userProgressDao(): UserProgressDao
    abstract fun gameSessionDao(): GameSessionDao
    abstract fun achievementDao(): AchievementDao
    abstract fun contentItemDao(): ContentItemDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
    abstract fun soundItemDao(): SoundItemDao
    abstract fun imageResourceDao(): ImageResourceDao
    
    companion object {
        const val DATABASE_NAME = "mete_education_database"
    }
}