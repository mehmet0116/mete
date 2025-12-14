package com.metegelistirme.cache

import android.content.Context
import androidx.core.content.edit
import com.metegelistirme.utils.BatteryOptimizer
import com.metegelistirme.utils.PerformanceOptimizer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.util.concurrent.TimeUnit

class AppCacheManager(private val context: Context) {
    
    companion object {
        private const val PREF_NAME = "app_cache_prefs"
        private const val KEY_LAST_CLEANUP = "last_cache_cleanup"
        private const val CLEANUP_INTERVAL = TimeUnit.DAYS.toMillis(7) // Cleanup every 7 days
        
        // Cache size limits (in bytes)
        private const val MAX_IMAGE_CACHE_SIZE = 50 * 1024 * 1024 // 50MB
        private const val MAX_AUDIO_CACHE_SIZE = 100 * 1024 * 1024 // 100MB
        private const val MAX_GENERAL_CACHE_SIZE = 20 * 1024 * 1024 // 20MB
    }
    
    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val ioScope = CoroutineScope(Dispatchers.IO)
    
    /**
     * Initialize cache management
     */
    fun initialize() {
        ioScope.launch {
            cleanupOldCacheIfNeeded()
            optimizeCacheSize()
        }
    }
    
    /**
     * Cleanup old cache files if needed
     */
    private suspend fun cleanupOldCacheIfNeeded() {
        val lastCleanup = preferences.getLong(KEY_LAST_CLEANUP, 0)
        val currentTime = System.currentTimeMillis()
        
        if (currentTime - lastCleanup > CLEANUP_INTERVAL) {
            Timber.d("Starting cache cleanup")
            
            cleanupDirectory(context.cacheDir)
            cleanupDirectory(context.externalCacheDir)
            
            preferences.edit {
                putLong(KEY_LAST_CLEANUP, currentTime)
            }
            
            Timber.d("Cache cleanup completed")
        }
    }
    
    /**
     * Optimize cache size based on device capabilities
     */
    private fun optimizeCacheSize() {
        val isLowEndDevice = PerformanceOptimizer.isLowEndDevice()
        val batterySettings = BatteryOptimizer.getBatteryAwareSettings(context)
        
        val maxCacheSize = when {
            isLowEndDevice || batterySettings.limitBackgroundTasks -> {
                // Reduce cache size for low-end devices or low battery
                MAX_IMAGE_CACHE_SIZE / 2
            }
            else -> {
                MAX_IMAGE_CACHE_SIZE
            }
        }
        
        Timber.d("Optimized cache size: ${maxCacheSize / (1024 * 1024)}MB")
    }
    
    /**
     * Cleanup specific directory
     */
    private fun cleanupDirectory(directory: File?) {
        directory?.let { dir ->
            if (dir.exists() && dir.isDirectory) {
                dir.listFiles()?.forEach { file ->
                    if (file.isFile && shouldDeleteFile(file)) {
                        file.delete()
                        Timber.d("Deleted cache file: ${file.name}")
                    }
                }
            }
        }
    }
    
    /**
     * Determine if file should be deleted
     */
    private fun shouldDeleteFile(file: File): Boolean {
        val lastModified = file.lastModified()
        val currentTime = System.currentTimeMillis()
        val fileAge = currentTime - lastModified
        
        // Delete files older than 30 days
        return fileAge > TimeUnit.DAYS.toMillis(30)
    }
    
    /**
     * Get current cache size
     */
    fun getCacheSize(): Long {
        return calculateDirectorySize(context.cacheDir) + 
               calculateDirectorySize(context.externalCacheDir)
    }
    
    /**
     * Calculate directory size recursively
     */
    private fun calculateDirectorySize(directory: File?): Long {
        return directory?.let { dir ->
            if (dir.exists()) {
                if (dir.isDirectory) {
                    dir.listFiles()?.sumOf { calculateDirectorySize(it) } ?: 0L
                } else {
                    dir.length()
                }
            } else {
                0L
            }
        } ?: 0L
    }
    
    /**
     * Clear all cache
     */
    fun clearAllCache() {
        ioScope.launch {
            clearDirectory(context.cacheDir)
            clearDirectory(context.externalCacheDir)
            Timber.d("All cache cleared")
        }
    }
    
    /**
     * Clear specific directory
     */
    private fun clearDirectory(directory: File?) {
        directory?.let { dir ->
            if (dir.exists() && dir.isDirectory) {
                dir.listFiles()?.forEach { file ->
                    if (file.isDirectory) {
                        clearDirectory(file)
                    }
                    file.delete()
                }
            }
        }
    }
    
    /**
     * Check if cache needs optimization
     */
    fun needsOptimization(): Boolean {
        val totalSize = getCacheSize()
        val maxAllowed = MAX_IMAGE_CACHE_SIZE + MAX_AUDIO_CACHE_SIZE + MAX_GENERAL_CACHE_SIZE
        
        return totalSize > maxAllowed * 0.8 // If cache is over 80% of limit
    }
}