package com.metegelistirme.utils

import android.content.Context
import android.os.BatteryManager
import android.os.PowerManager
import timber.log.Timber

object BatteryOptimizer {
    
    private const val TAG = "BatteryOptimizer"
    
    /**
     * Check if device is charging
     */
    fun isCharging(context: Context): Boolean {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        
        return batteryManager.isCharging
    }
    
    /**
     * Get battery level
     */
    fun getBatteryLevel(context: Context): Int {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        
        return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
    
    /**
     * Check if battery saver is enabled
     */
    fun isBatterySaverEnabled(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        
        return powerManager.isPowerSaveMode
    }
    
    /**
     * Adjust app behavior based on battery level
     */
    fun getBatteryAwareSettings(context: Context): BatteryAwareSettings {
        val batteryLevel = getBatteryLevel(context)
        val isCharging = isCharging(context)
        val isBatterySaver = isBatterySaverEnabled(context)
        
        return when {
            batteryLevel < 20 || isBatterySaver -> BatteryAwareSettings(
                reduceAnimations = true,
                limitBackgroundTasks = true,
                lowerImageQuality = true,
                disableAutoSync = true
            )
            batteryLevel < 50 && !isCharging -> BatteryAwareSettings(
                reduceAnimations = false,
                limitBackgroundTasks = true,
                lowerImageQuality = false,
                disableAutoSync = false
            )
            else -> BatteryAwareSettings(
                reduceAnimations = false,
                limitBackgroundTasks = false,
                lowerImageQuality = false,
                disableAutoSync = false
            )
        }
    }
    
    data class BatteryAwareSettings(
        val reduceAnimations: Boolean,
        val limitBackgroundTasks: Boolean,
        val lowerImageQuality: Boolean,
        val disableAutoSync: Boolean
    )
    
    /**
     * Optimize background tasks based on battery
     */
    fun shouldRunBackgroundTask(context: Context, taskType: BackgroundTaskType): Boolean {
        val settings = getBatteryAwareSettings(context)
        
        return when (taskType) {
            BackgroundTaskType.SYNC -> !settings.disableAutoSync
            BackgroundTaskType.ANALYTICS -> !settings.limitBackgroundTasks
            BackgroundTaskType.CACHE_CLEANUP -> true // Always run cleanup
            BackgroundTaskType.DATA_PREFETCH -> !settings.limitBackgroundTasks && !settings.disableAutoSync
        }
    }
    
    enum class BackgroundTaskType {
        SYNC, ANALYTICS, CACHE_CLEANUP, DATA_PREFETCH
    }
}