package com.metegelistirme.activities.modules

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.activities.modules.cognitive.MemoryCardsActivity
import com.metegelistirme.activities.modules.cognitive.FindDifferenceActivity

class CognitiveModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cognitive_module)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Bilişsel Gelişim"

        setupLessons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupLessons() {
        findViewById<MaterialCardView>(R.id.cardMemory).setOnClickListener {
            startActivity(Intent(this, MemoryCardsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardDifference).setOnClickListener {
            startActivity(Intent(this, FindDifferenceActivity::class.java))
        }
    }
}
