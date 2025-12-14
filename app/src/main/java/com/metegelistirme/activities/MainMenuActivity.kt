package com.metegelistirme.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var buttonClickSound: MediaPlayer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupClickListeners()
    }
    
    private fun setupUI() {
        // Arka plan müziği başlatılabilir (isteğe bağlı)
        // setupBackgroundMusic()
    }
    
    private fun setupClickListeners() {
        binding.educationButton.setOnClickListener {
            playButtonClickSound()
            startActivity(Intent(this, EducationActivity::class.java))
        }
        
        binding.gamesButton.setOnClickListener {
            playButtonClickSound()
            startActivity(Intent(this, GamesActivity::class.java))
        }
        
        binding.settingsButton.setOnClickListener {
            playButtonClickSound()
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        
        binding.parentButton.setOnClickListener {
            playButtonClickSound()
            startActivity(Intent(this, ParentActivity::class.java))
        }
    }
    
    private fun playButtonClickSound() {
        try {
            val soundResId = resources.getIdentifier("button_click", "raw", packageName)
            if (soundResId != 0) {
                buttonClickSound = MediaPlayer.create(this, soundResId)
                buttonClickSound.setOnCompletionListener {
                    it.release()
                }
                buttonClickSound.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::buttonClickSound.isInitialized && buttonClickSound.isPlaying) {
            buttonClickSound.stop()
            buttonClickSound.release()
        }
    }
}