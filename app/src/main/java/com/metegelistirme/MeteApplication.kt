package com.metegelistirme

import android.app.Application
import com.metegelistirme.database.AppDatabase

class MeteApplication : Application() {
    
    companion object {
        lateinit var instance: MeteApplication
            private set
    }
    
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}