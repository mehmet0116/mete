package com.metegelistirme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MeteApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Timber logging initialization
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize other components if needed
        initializeAppComponents()
    }
    
    private fun initializeAppComponents() {
        // App-wide initialization logic
        Timber.d("MeteApplication initialized")
    }
}