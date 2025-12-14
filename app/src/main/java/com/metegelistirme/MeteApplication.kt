package com.metegelistirme

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.security.ProviderInstaller
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

@HiltAndroidApp
class MeteApplication : Application() {
    
    companion object {
        // DataStore instance
        private val Context.dataStore by preferencesDataStore(name = "mete_preferences")
        
        // Get DataStore instance
        fun getDataStore(context: Context) = context.dataStore
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Timber for logging (debug builds only)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        
        // Initialize security provider
        initializeSecurityProvider()
        
        // Initialize app components
        initializeAppComponents()
        
        Timber.d("MeteApplication initialized")
    }
    
    private fun initializeSecurityProvider() {
        try {
            // Update security provider to protect against SSL vulnerabilities
            ProviderInstaller.installIfNeeded(applicationContext)
            
            val sslContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            sslContext.createSSLEngine()
            
        } catch (e: GooglePlayServicesRepairableException) {
            Timber.e(e, "Google Play Services not available")
        } catch (e: GooglePlayServicesNotAvailableException) {
            Timber.e(e, "Google Play Services not available")
        } catch (e: NoSuchAlgorithmException) {
            Timber.e(e, "SSL algorithm not available")
        } catch (e: KeyManagementException) {
            Timber.e(e, "Key management exception")
        }
    }
    
    private fun initializeAppComponents() {
        // Initialize any app-wide components here
        // For example: WorkManager, Notifications, Analytics, etc.
        
        // Set default uncaught exception handler
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Timber.e(throwable, "Uncaught exception in thread: ${thread.name}")
            // You could log to Firebase Crashlytics here
        }
    }
    
    override fun onLowMemory() {
        super.onLowMemory()
        Timber.w("Low memory warning - clearing caches if needed")
        // Clear image caches, temporary files, etc.
    }
    
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        when (level) {
            TRIM_MEMORY_COMPLETE -> {
                Timber.w("TRIM_MEMORY_COMPLETE - app is in background and system is low on memory")
            }
            TRIM_MEMORY_MODERATE -> {
                Timber.w("TRIM_MEMORY_MODERATE - app is in background and system is moderately low on memory")
            }
            TRIM_MEMORY_BACKGROUND -> {
                Timber.w("TRIM_MEMORY_BACKGROUND - app is in background and system is running low on memory")
            }
            TRIM_MEMORY_UI_HIDDEN -> {
                Timber.w("TRIM_MEMORY_UI_HIDDEN - app's UI is no longer visible")
            }
        }
    }
}