package com.metegelistirme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MeteApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Timber logging setup
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize other components here
        initializeAppComponents()
    }
    
    private fun initializeAppComponents() {
        // App initialization logic
        Timber.d("MeteApplication initialized")
    }
}