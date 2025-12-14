package com.metegelistirme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metegelistirme.database.entities.ProgressEntity
import com.metegelistirme.repository.EducationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    
    init {
        loadProgress()
    }
    
    private fun loadProgress() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getAllProgress()
                    .catch { error ->
                        Timber.e(error, "Error loading progress")
                        _errorMessage.value = "İlerleme yüklenirken hata oluştu"
                    }
                    .collect { progressList ->
                        _uiState.value = _uiState.value.copy(
                            progressLoaded = true,
                            totalProgress = progressList.size
                        )
                    }
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun saveProgress(moduleId: String, progress: Int) {
        viewModelScope.launch {
            try {
                val progressEntity = ProgressEntity(
                    moduleId = moduleId,
                    progress = progress,
                    timestamp = System.currentTimeMillis()
                )
                repository.saveProgress(progressEntity)
                Timber.d("Progress saved for module: $moduleId")
            } catch (e: Exception) {
                Timber.e(e, "Error saving progress")
                _errorMessage.value = "İlerleme kaydedilirken hata oluştu"
            }
        }
    }
    
    fun clearError() {
        _errorMessage.value = null
    }
    
    override fun onCleared() {
        super.onCleared()
        Timber.d("MainViewModel cleared")
    }
}

data class MainUiState(
    val progressLoaded: Boolean = false,
    val totalProgress: Int = 0
)
