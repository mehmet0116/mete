package com.metegelistirme.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.metegelistirme.MeteApplication

object AppPreferences {
    
    private const val PREFS_NAME = "mete_preferences"
    private const val KEY_FIRST_LAUNCH = "first_launch"
    private const val KEY_SOUND_ENABLED = "sound_enabled"
    private const val KEY_MUSIC_ENABLED = "music_enabled"
    private const val KEY_DARK_MODE = "dark_mode"
    private const val KEY_CHILD_NAME = "child_name"
    private const val KEY_CHILD_AGE = "child_age"
    private const val KEY_LAST_ACTIVITY = "last_activity"
    private const val KEY_DAILY_TIME_LIMIT = "daily_time_limit"
    private const val KEY_TOTAL_PLAY_TIME = "total_play_time"
    private const val KEY_COMPLETED_MODULES = "completed_modules"
    private const val KEY_STARS_COLLECTED = "stars_collected"
    
    private lateinit var prefs: SharedPreferences
    
    fun initialize(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    var isFirstLaunch: Boolean
        get() = prefs.getBoolean(KEY_FIRST_LAUNCH, true)
        set(value) = prefs.edit { putBoolean(KEY_FIRST_LAUNCH, value) }
    
    var isSoundEnabled: Boolean
        get() = prefs.getBoolean(KEY_SOUND_ENABLED, true)
        set(value) = prefs.edit { putBoolean(KEY_SOUND_ENABLED, value) }
    
    var isMusicEnabled: Boolean
        get() = prefs.getBoolean(KEY_MUSIC_ENABLED, true)
        set(value) = prefs.edit { putBoolean(KEY_MUSIC_ENABLED, value) }
    
    var isDarkMode: Boolean
        get() = prefs.getBoolean(KEY_DARK_MODE, false)
        set(value) = prefs.edit { putBoolean(KEY_DARK_MODE, value) }
    
    var childName: String
        get() = prefs.getString(KEY_CHILD_NAME, "") ?: ""
        set(value) = prefs.edit { putString(KEY_CHILD_NAME, value) }
    
    var childAge: Int
        get() = prefs.getInt(KEY_CHILD_AGE, 5)
        set(value) = prefs.edit { putInt(KEY_CHILD_AGE, value) }
    
    var lastActivity: String
        get() = prefs.getString(KEY_LAST_ACTIVITY, "") ?: ""
        set(value) = prefs.edit { putString(KEY_LAST_ACTIVITY, value) }
    
    var dailyTimeLimit: Int
        get() = prefs.getInt(KEY_DAILY_TIME_LIMIT, 30) // 30 minutes default
        set(value) = prefs.edit { putInt(KEY_DAILY_TIME_LIMIT, value) }
    
    var totalPlayTime: Long
        get() = prefs.getLong(KEY_TOTAL_PLAY_TIME, 0L)
        set(value) = prefs.edit { putLong(KEY_TOTAL_PLAY_TIME, value) }
    
    var completedModules: Set<String>
        get() = prefs.getStringSet(KEY_COMPLETED_MODULES, emptySet()) ?: emptySet()
        set(value) = prefs.edit { putStringSet(KEY_COMPLETED_MODULES, value) }
    
    var starsCollected: Int
        get() = prefs.getInt(KEY_STARS_COLLECTED, 0)
        set(value) = prefs.edit { putInt(KEY_STARS_COLLECTED, value) }
    
    fun addCompletedModule(moduleId: String) {
        val currentSet = completedModules.toMutableSet()
        currentSet.add(moduleId)
        completedModules = currentSet
    }
    
    fun addStars(count: Int) {
        starsCollected += count
    }
    
    fun resetProgress() {
        prefs.edit {
            remove(KEY_COMPLETED_MODULES)
            remove(KEY_STARS_COLLECTED)
            remove(KEY_TOTAL_PLAY_TIME)
            remove(KEY_LAST_ACTIVITY)
        }
    }
    
    fun clearAll() {
        prefs.edit {
            clear()
        }
    }
}