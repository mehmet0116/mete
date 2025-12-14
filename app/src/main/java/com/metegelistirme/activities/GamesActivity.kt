package com.metegelistirme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class GamesActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        
        // Oyun modülleri burada yüklenecek
        setupGameModules()
    }
    
    private fun setupGameModules() {
        // Oyun türleri için gerekli kurulum
        // Eşleştirme, puzzle, hafıza oyunları vb.
    }
}