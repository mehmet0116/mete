package com.metegelistirme.activities.modules

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.activities.modules.math.CountingActivity
import com.metegelistirme.activities.modules.math.AdditionActivity
import com.metegelistirme.activities.modules.math.SubtractionActivity
import com.metegelistirme.activities.modules.math.ShapesActivity

class MathModuleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_module)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Matematik"

        setupLessons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupLessons() {
        findViewById<MaterialCardView>(R.id.cardCounting).setOnClickListener {
            startActivity(Intent(this, CountingActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardAddition).setOnClickListener {
            startActivity(Intent(this, AdditionActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardSubtraction).setOnClickListener {
            startActivity(Intent(this, SubtractionActivity::class.java))
        }

        findViewById<MaterialCardView>(R.id.cardShapes).setOnClickListener {
            startActivity(Intent(this, ShapesActivity::class.java))
        }
    }
}
