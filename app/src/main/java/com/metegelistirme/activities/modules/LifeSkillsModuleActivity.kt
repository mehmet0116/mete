package com.metegelistirme.activities.modules

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.activities.modules.lifeskills.ColorsActivity
import com.metegelistirme.activities.modules.lifeskills.AnimalsActivity

class LifeSkillsModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifeskills_module)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Günlük Yaşam"

        setupLessons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupLessons() {
        findViewById<MaterialCardView>(R.id.cardColors).setOnClickListener {
            startActivity(Intent(this, ColorsActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardAnimals).setOnClickListener {
            startActivity(Intent(this, AnimalsActivity::class.java))
        }
    }
}
