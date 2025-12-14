package com.metegelistirme.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val splashDelay: Long = 3000 // 3 saniye
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Hoş geldin sesini çal
        playWelcomeSound()
        
        // Splash ekranını göster ve ana menüye geç
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMainMenu()
        }, splashDelay)
    }
    
    private fun playWelcomeSound() {
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.welcome_sound)
            mediaPlayer.setOnCompletionListener {
                it.release()
            }
            mediaPlayer.start()
        } catch (e: Exception) {
            e.printStackTrace()
            // Ses dosyası yoksa sessiz devam et
        }
    }
    
    private fun navigateToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}