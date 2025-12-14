package com.metegelistirme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class SettingsActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        // Ayarlar burada yüklenecek
        setupSettings()
    }
    
    private fun setupSettings() {
        // Ses ayarları, tema, dil seçenekleri vb.
    }
}