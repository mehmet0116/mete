package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class MainMenuActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        
        // Butonları bul
        val btnEducation = findViewById<Button>(R.id.btn_education)
        val btnGames = findViewById<Button>(R.id.btn_games)
        val btnSettings = findViewById<Button>(R.id.btn_settings)
        val btnParent = findViewById<Button>(R.id.btn_parent)
        
        // Buton tıklama olayları
        btnEducation.setOnClickListener {
            val intent = Intent(this, EducationActivity::class.java)
            startActivity(intent)
        }
        
        btnGames.setOnClickListener {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
        }
        
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        
        btnParent.setOnClickListener {
            val intent = Intent(this, ParentActivity::class.java)
            startActivity(intent)
        }
    }
}