package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize UI
        setupUI()
        
        // Navigate to main menu after delay
        navigateToMainMenu()
    }
    
    private fun setupUI() {
        // Set up any animations or UI elements here
        binding.apply {
            // You can add Lottie animations here
            // animationView.setAnimation(R.raw.splash_animation)
        }
    }
    
    private fun navigateToMainMenu() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
            
            // Add custom transition animation
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }, 1500) // 1.5 second delay
    }
    
    override fun onBackPressed() {
        // Disable back button during splash screen
        // to prevent interrupting the flow
    }
}