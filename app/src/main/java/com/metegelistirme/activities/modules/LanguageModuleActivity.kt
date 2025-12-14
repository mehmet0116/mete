package com.metegelistirme.activities.modules

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R

class LanguageModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_module)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupLessons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupLessons() {
        // Alfabe Dersi
        findViewById<MaterialCardView>(R.id.cardAlphabet).setOnClickListener {
            Toast.makeText(this, "🔤 Alfabe öğreniyoruz!", Toast.LENGTH_SHORT).show()
            // TODO: Alfabe oyunu başlat
        }

        // Kelime Öğrenme
        findViewById<MaterialCardView>(R.id.cardWords).setOnClickListener {
            Toast.makeText(this, "📖 Kelime öğreniyoruz!", Toast.LENGTH_SHORT).show()
        }

        // Cümle Kurma
        findViewById<MaterialCardView>(R.id.cardSentences).setOnClickListener {
            Toast.makeText(this, "✍️ Cümle kuruyoruz!", Toast.LENGTH_SHORT).show()
        }

        // Hikaye Dinleme
        findViewById<MaterialCardView>(R.id.cardStories).setOnClickListener {
            Toast.makeText(this, "📚 Hikaye dinliyoruz!", Toast.LENGTH_SHORT).show()
        }
    }
}
