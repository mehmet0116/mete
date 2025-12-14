package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.activities.games.MatchingGameActivity
import com.metegelistirme.activities.games.SimplePuzzleActivity
import com.metegelistirme.databinding.ActivityGamesBinding

class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar setup
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupGameModules()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupGameModules() {
        binding.cardMatchingGame.setOnClickListener {
            val intent = Intent(this, MatchingGameActivity::class.java)
            startActivity(intent)
        }

        binding.cardPuzzleGame.setOnClickListener {
            val intent = Intent(this, SimplePuzzleActivity::class.java)
            startActivity(intent)
        }

        binding.cardMemoryGame.setOnClickListener {
            Toast.makeText(this, "🧠 Hafıza Oyunu yakında!", Toast.LENGTH_SHORT).show()
        }

        binding.cardQuizGame.setOnClickListener {
            Toast.makeText(this, "🎤 Sesli Quiz yakında!", Toast.LENGTH_SHORT).show()
        }
    }
}
