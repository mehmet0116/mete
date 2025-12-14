package com.metegelistirme.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Debug
import timber.log.Timber
import kotlin.math.roundToInt

object MemoryManager {
    
    private const val TAG = "MemoryManager"
    
    /**
     * Get current memory usage
     */
    fun getMemoryUsage(context: Context): MemoryUsage {
        val runtime = Runtime.getRuntime()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        
        val usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)
        val maxMemory = runtime.maxMemory() / (1024 * 1024)
        val availableMemory = runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory()
        
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        
        val nativeHeapSize = Debug.getNativeHeapSize() / (1024 * 1024)
        val nativeHeapAllocated = Debug.getNativeHeapAllocatedSize() / (1024 * 1024)
        val nativeHeapFree = Debug.getNativeHeapFreeSize() / (1024 * 1024)
        
        return MemoryUsage(
            usedMemoryMB = usedMemory,
            maxMemoryMB = maxMemory,
            availableMemoryMB = availableMemory / (1024 * 1024),
            totalSystemMemoryMB = memoryInfo.totalMem / (1024 * 1024),
            availableSystemMemoryMB = memoryInfo.availMem / (1024 * 1024),
            lowMemory = memoryInfo.lowMemory,
            thresholdMB = memoryInfo.threshold / (1024 * 1024),
            nativeHeapSizeMB = nativeHeapSize,
            nativeHeapAllocatedMB = nativeHeapAllocated,
            nativeHeapFreeMB = nativeHeapFree,
            usagePercentage = (usedMemory.toFloat() / maxMemory.toFloat() * 100).roundToInt()
        )
    }
    
    /**
     * Check if memory is critically low
     */
    fun isMemoryCritical(context: Context): Boolean {
        val usage = getMemoryUsage(context)
        return usage.usagePercentage > 90 || usage.lowMemory
    }
    
    /**
     * Get memory optimization recommendations
     */
    fun getOptimizationRecommendations(context: Context): List<OptimizationRecommendation> {
        val recommendations = mutableListOf<OptimizationRecommendation>()
        val usage = getMemoryUsage(context)
        
        if (usage.usagePercentage > 80) {
            recommendations.add(OptimizationRecommendation.CLEAR_IMAGE_CACHE)
        }
        
        if (usage.usagePercentage > 70) {
            recommendations.add(OptimizationRecommendation.REDUCE_ANIMATIONS)
        }
        
        if (usage.nativeHeapAllocatedMB > usage.nativeHeapSizeMB * 0.8) {
            recommendations.add(OptimizationRecommendation.OPTIMIZE_NATIVE_MEMORY)
        }
        
        if (usage.lowMemory) {
            recommendations.add(OptimizationRecommendation.LIMIT_BACKGROUND_TASKS)
            recommendations.add(OptimizationRecommendation.REDUCE_UI_COMPLEXITY)
        }
        
        return recommendations
    }
    
    /**
     * Force garbage collection (use sparingly)
     */
    fun forceGarbageCollection() {
        System.gc()
        System.runFinalization()
        Timber.d("Forced garbage collection")
    }
    
    /**
     * Monitor memory leaks (debug only)
     */
    fun checkForMemoryLeaks() {
        if (BuildConfig.DEBUG) {
            val runtime = Runtime.getRuntime()
            val usedMemory = runtime.totalMemory() - runtime.freeMemory()
            
            Timber.d("Memory usage: ${usedMemory / (1024 * 1024)}MB")
            
            // Add memory leak detection logic here
            // Consider integrating with LeakCanary
        }
    }
    
    data class MemoryUsage(
        val usedMemoryMB: Long,
        val maxMemoryMB: Long,
        val availableMemoryMB: Long,
        val totalSystemMemoryMB: Long,
        val availableSystemMemoryMB: Long,
        val lowMemory: Boolean,
        val thresholdMB: Long,
        val nativeHeapSizeMB: Long,
        val nativeHeapAllocatedMB: Long,
        val nativeHeapFreeMB: Long,
        val usagePercentage: Int
    )
    
    enum class OptimizationRecommendation {
        CLEAR_IMAGE_CACHE,
        REDUCE_ANIMATIONS,
        OPTIMIZE_NATIVE_MEMORY,
        LIMIT_BACKGROUND_TASKS,
        REDUCE_UI_COMPLEXITY,
        USE_MEMORY_EFFICIENT_IMAGES,
        IMPLEMENT_LAZY_LOADING,
        OPTIMIZE_DATA_STRUCTURES
    }
}