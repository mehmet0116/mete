package com.metegelistirme.activities.modules

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.activities.modules.creativity.DrawingActivity
import com.metegelistirme.activities.modules.creativity.MusicActivity

class CreativityModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creativity_module)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Yaratıcılık"

        setupLessons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupLessons() {
        findViewById<MaterialCardView>(R.id.cardDrawing).setOnClickListener {
            startActivity(Intent(this, DrawingActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardMusic).setOnClickListener {
            startActivity(Intent(this, MusicActivity::class.java))
        }
    }
}
