package com.metegelistirme

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import com.metegelistirme.database.AppDatabase
import com.metegelistirme.utils.AppPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

@HiltAndroidApp
class MeteApplication : Application() {
    
    companion object {
        lateinit var instance: MeteApplication
            private set
    }
    
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    // Lazy initialization for database
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "mete_education_database"
        )
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                applicationScope.launch {
                    // Pre-populate database if needed
                }
            }
        })
        .setQueryExecutor { command -> 
            applicationScope.launch { command.run() }
        }
        .setTransactionExecutor { command ->
            applicationScope.launch { command.run() }
        }
        .enableMultiInstanceInvalidation()
        .fallbackToDestructiveMigration()
        .build()
    }
    
    // Lazy initialization for DataStore
    val dataStore by lazy {
        PreferenceDataStoreFactory.create(
            scope = applicationScope,
            produceFile = { applicationContext.preferencesDataStoreFile("settings") }
        )
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Initialize Timber for logging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize app preferences
        AppPreferences.initialize(this)
        
        // Update security provider for better TLS
        updateAndroidSecurityProvider()
        
        // Preload resources in background
        preloadResources()
        
        // Initialize analytics (if any)
        initializeAnalytics()
    }
    
    private fun updateAndroidSecurityProvider() {
        try {
            ProviderInstaller.installIfNeeded(applicationContext)
            
            val sslContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
        } catch (e: GooglePlayServicesRepairableException) {
            Timber.e(e, "Google Play Services not available")
        } catch (e: GooglePlayServicesNotAvailableException) {
            Timber.e(e, "Google Play Services not available")
        } catch (e: NoSuchAlgorithmException) {
            Timber.e(e, "No such algorithm")
        } catch (e: KeyManagementException) {
            Timber.e(e, "Key management exception")
        }
    }
    
    private fun preloadResources() {
        applicationScope.launch {
            // Preload sounds, images, or other resources
            Timber.d("Preloading resources...")
            
            // Example: Preload Lottie animations
            // Example: Preload common sounds
            
            Timber.d("Resources preloaded")
        }
    }
    
    private fun initializeAnalytics() {
        // Initialize analytics services here
        // Example: Firebase Analytics, Crashlytics, etc.
    }
    
    fun getAppContext(): Context = applicationContext
}