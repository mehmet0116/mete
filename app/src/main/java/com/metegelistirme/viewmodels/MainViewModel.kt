package com.metegelistirme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.metegelistirme.repository.EducationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EducationRepository
) : ViewModel() {
    
    // State for UI
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    
    // Loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    // Error state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    
    // Cache for frequently accessed data
    private val moduleCache = mutableMapOf<String, LearningModule>()
    private var cacheJob: Job? = null
    
    init {
        // Preload data on initialization
        preloadEssentialData()
        
        // Setup error handling
        setupErrorHandling()
    }
    
    private fun preloadEssentialData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Preload learning modules
                repository.getAllLearningModules()
                    .firstOrNull()
                    ?.forEach { module ->
                        moduleCache[module.id] = module
                        
                        // Preload audio resources for first module
                        if (module.order == 1) {
                            repository.preloadAudioResources(module.id)
                        }
                    }
                
                Timber.d("Preloaded ${moduleCache.size} modules")
                _uiState.value = _uiState.value.copy(
                    modulesLoaded = true
                )
            } catch (e: Exception) {
                Timber.e(e, "Error preloading data")
                _errorMessage.value = "Veriler yüklenirken hata oluştu"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun getLearningModules(): Flow<List<LearningModule>> {
        return repository.getAllLearningModules()
            .onEach { modules ->
                // Update cache
                modules.forEach { module ->
                    moduleCache[module.id] = module
                }
            }
            .catch { error ->
                Timber.e(error, "Error fetching learning modules")
                _errorMessage.value = "Modüller yüklenirken hata oluştu"
                emit(emptyList())
            }
    }
    
    fun getCachedModule(moduleId: String): LearningModule? {
        return moduleCache[moduleId]
    }
    
    fun saveGameScore(gameType: String, score: Int, userId: String) {
        viewModelScope.launch {
            try {
                val gameScore = GameScore(
                    gameType = gameType,
                    score = score,
                    userId = userId,
                    timestamp = System.currentTimeMillis()
                )
                
                repository.saveGameScore(gameScore)
                
                // Check for achievements
                checkAchievements(userId, gameType, score)
                
            } catch (e: Exception) {
                Timber.e(e, "Error saving game score")
                _errorMessage.value = "Skor kaydedilirken hata oluştu"
            }
        }
    }
    
    private fun checkAchievements(userId: String, gameType: String, score: Int) {
        viewModelScope.launch {
            // Implement achievement checking logic
            // Example: If score > 100, unlock achievement
            if (score > 100) {
                repository.unlockAchievement("high_score_$gameType", userId)
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    private fun setupErrorHandling() {
        viewModelScope.launch {
            errorMessage.collect { error ->
                if (error != null) {
                    // Log error to analytics
                    Timber.e("UI Error: $error")
                    
                    // Auto-clear error after 5 seconds
                    launch {
                        kotlinx.coroutines.delay(5000)
                        if (_errorMessage.value == error) {
                            clearError()
                        }
                    }
                }
            }
        }
    }
    
    override fun onCleared() {
        super.onCleared()
        cacheJob?.cancel()
        moduleCache.clear()
    }
    
    data class MainUiState(
        val modulesLoaded: Boolean = false,
        val currentModule: String? = null,
        val userProgress: Int = 0
    )
}