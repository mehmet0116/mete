package com.metegelistirme.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val splashDelay: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Play welcome sound
        playWelcomeSound()

        // Navigate to main menu after delay
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToMainMenu()
        }, splashDelay)
    }
    
    private fun playWelcomeSound() {
        try {
            val soundResId = resources.getIdentifier("welcome_sound", "raw", packageName)
            if (soundResId != 0) {
                mediaPlayer = MediaPlayer.create(this, soundResId)
                mediaPlayer.setOnCompletionListener {
                    it.release()
                }
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            Timber.e(e, "Error playing welcome sound")
        }
    }
    
    private fun navigateToMainMenu() {
        val intent = Intent(this, MainMenuActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        Timber.d("SplashActivity destroyed")
    }
}