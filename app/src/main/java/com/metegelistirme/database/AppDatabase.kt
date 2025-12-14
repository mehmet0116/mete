package com.metegelistirme.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.metegelistirme.database.converters.DateConverter
import com.metegelistirme.database.converters.ListConverter
import com.metegelistirme.database.dao.*
import com.metegelistirme.database.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@Database(
    entities = [
        ProgressEntity::class,
        DummyEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class, ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mete_education.db"
                )
                .addCallback(DatabaseCallback(context))
                .setQueryExecutor { command ->
                    // Use IO dispatcher for database queries
                    CoroutineScope(Dispatchers.IO).launch {
                        command.run()
                    }
                }
                .enableMultiInstanceInvalidation()
                .setJournalMode(JournalMode.TRUNCATE)
                .build()
                
                INSTANCE = instance
                instance
            }
        }
        
        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Timber.d("Database created")
                
                // Pre-populate database with initial data
                CoroutineScope(Dispatchers.IO).launch {
                    prePopulateDatabase(context)
                }
            }
            
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // Enable WAL for better concurrency
                db.execSQL("PRAGMA journal_mode = WAL")
                // Enable foreign keys
                db.execSQL("PRAGMA foreign_keys = ON")
                // Set cache size
                db.execSQL("PRAGMA cache_size = -2000") // 2MB cache
                Timber.d("Database optimized with WAL mode")
            }
        }
        
        private suspend fun prePopulateDatabase(context: Context) {
            // Pre-populate with initial learning modules, audio resources, etc.
            // This will be called only once when database is created
            Timber.d("Pre-populating database...")
            
            // TODO: Add initial data insertion logic here
            // Example: Insert default learning modules, audio files metadata, etc.
        }
    }
}