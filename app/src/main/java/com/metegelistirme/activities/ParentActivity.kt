package com.metegelistirme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class ParentActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        
        // Ebeveyn kontrolleri burada yüklenecek
        setupParentControls()
    }
    
    private fun setupParentControls() {
        // İlerleme takibi, süre sınırlaması, raporlar vb.
    }
}