package com.metegelistirme.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.activities.modules.LanguageModuleActivity
import com.metegelistirme.databinding.ActivityEducationBinding

class EducationActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityEducationBinding
    private lateinit var buttonClickSound: MediaPlayer
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupClickListeners()
    }
    
    private fun setupUI() {
        // Başlık ayarla
        supportActionBar?.title = getString(R.string.education_button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    private fun setupClickListeners() {
        binding.languageCard.setOnClickListener {
            playButtonClickSound()
            startActivity(Intent(this, LanguageModuleActivity::class.java))
        }
        
        binding.mathCard.setOnClickListener {
            playButtonClickSound()
            // TODO: Matematik modülü aktivitesi eklenecek
            showComingSoonMessage()
        }
        
        binding.cognitiveCard.setOnClickListener {
            playButtonClickSound()
            // TODO: Bilişsel gelişim modülü eklenecek
            showComingSoonMessage()
        }
        
        binding.creativityCard.setOnClickListener {
            playButtonClickSound()
            // TODO: Yaratıcılık modülü eklenecek
            showComingSoonMessage()
        }
        
        binding.lifeSkillsCard.setOnClickListener {
            playButtonClickSound()
            // TODO: Günlük yaşam modülü eklenecek
            showComingSoonMessage()
        }
    }
    
    private fun playButtonClickSound() {
        try {
            buttonClickSound = MediaPlayer.create(this, R.raw.button_click)
            buttonClickSound.setOnCompletionListener {
                it.release()
            }
            buttonClickSound.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun showComingSoonMessage() {
        // TODO: Yakında eklenecek mesajı göster
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::buttonClickSound.isInitialized && buttonClickSound.isPlaying) {
            buttonClickSound.stop()
            buttonClickSound.release()
        }
    }
}