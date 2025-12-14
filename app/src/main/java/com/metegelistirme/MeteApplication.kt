package com.metegelistirme

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class MeteApplication : Application() {
    
    companion object {
        lateinit var instance: MeteApplication
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Uygulama başlangıç ayarları
        setupAppSettings()
    }
    
    private fun setupAppSettings() {
        // Varsayılan olarak sistem temasını takip et
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
    fun getAppContext(): Context = applicationContext
}