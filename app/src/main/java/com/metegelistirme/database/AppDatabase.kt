package com.metegelistirme.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.metegelistirme.database.dao.ProgressDao
import com.metegelistirme.database.entities.DummyEntity
import com.metegelistirme.database.entities.ProgressEntity

@Database(
    entities = [
        ProgressEntity::class,
        DummyEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mete_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
