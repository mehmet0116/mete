package com.metegelistirme

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.metegelistirme.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// DataStore instance
private val Context.dataStore by preferencesDataStore(name = "mete_preferences")

@HiltAndroidApp
class MeteApplication : Application() {
    
    companion object {
        lateinit var instance: MeteApplication
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Timber logging setup (only in debug)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize app components
        initializeApp()
    }
    
    private fun initializeApp() {
        // Preload common resources
        preloadResources()
        
        // Setup exception handler
        setupExceptionHandler()
        
        // Initialize database in background
        initializeDatabaseAsync()
    }
    
    private fun preloadResources() {
        // Preload common drawables and sounds
        // This reduces UI lag when resources are first accessed
        try {
            // Preload theme colors
            resources.getColor(android.R.color.transparent, null)
            
            // Preload common animations
            // Resources will be cached for faster access
        } catch (e: Exception) {
            Timber.e(e, "Error preloading resources")
        }
    }
    
    private fun setupExceptionHandler() {
        // Set default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Timber.e(throwable, "Uncaught exception in thread: ${thread.name}")
            
            // Log to analytics if needed
            logCrashAnalytics(throwable)
            
            // Re-throw to default handler
            Thread.getDefaultUncaughtExceptionHandler()?.uncaughtException(thread, throwable)
        }
    }
    
    private fun initializeDatabaseAsync() {
        // Initialize Room database in background thread
        Thread {
            try {
                // This will trigger database creation
                AppDatabase.getInstance(this)
                Timber.d("Database initialized successfully")
            } catch (e: Exception) {
                Timber.e(e, "Failed to initialize database")
            }
        }.start()
    }
    
    private fun logCrashAnalytics(throwable: Throwable) {
        // Implement crash analytics logging here
        // For example: Firebase Crashlytics, Sentry, etc.
    }
    
    // Helper function to get DataStore
    fun getDataStore() = dataStore
}