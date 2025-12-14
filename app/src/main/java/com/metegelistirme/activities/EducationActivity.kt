package com.metegelistirme.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class EducationActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)
        
        // Eğitim modülleri burada yüklenecek
        setupEducationModules()
    }
    
    private fun setupEducationModules() {
        // Eğitim modülleri için gerekli kurulum
        // Dil gelişimi, matematik, bilişsel gelişim vb.
    }
}