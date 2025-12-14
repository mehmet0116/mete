package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnEducation.setOnClickListener {
            startActivity(Intent(this, EducationActivity::class.java))
        }

        binding.btnGames.setOnClickListener {
            startActivity(Intent(this, GamesActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.btnParent.setOnClickListener {
            startActivity(Intent(this, ParentActivity::class.java))
        }
    }
}
