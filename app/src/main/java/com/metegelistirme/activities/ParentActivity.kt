package com.metegelistirme.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.metegelistirme.R

class ParentActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        
        // Toolbar setup
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupParentControls()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupParentControls() {
        // Süre Sınırlama Slider
        val sliderTimeLimit = findViewById<Slider>(R.id.sliderTimeLimit)
        sliderTimeLimit.addOnChangeListener { _, value, _ ->
            Toast.makeText(this, "Günlük süre: ${value.toInt()} dakika", Toast.LENGTH_SHORT).show()
        }

        // Süre Sınırlama Switch
        val switchTimeLimit = findViewById<SwitchMaterial>(R.id.switchTimeLimit)
        switchTimeLimit.setOnCheckedChangeListener { _, isChecked ->
            sliderTimeLimit.isEnabled = isChecked
            val status = if(isChecked) "aktif" else "pasif"
            Toast.makeText(this, "Süre sınırlaması $status", Toast.LENGTH_SHORT).show()
        }

        // Tüm butonlar için listener'lar
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        // Layout'tan butonları bul ve listener ekle
        val detailedReportButton = findViewById<MaterialButton>(R.id.btnDetailedReport)
        detailedReportButton?.setOnClickListener {
            Toast.makeText(this, "📊 Detaylı rapor açılıyor...", Toast.LENGTH_SHORT).show()
            // TODO: Detaylı rapor ekranını aç
        }

        val allActivitiesButton = findViewById<MaterialButton>(R.id.btnAllActivities)
        allActivitiesButton?.setOnClickListener {
            Toast.makeText(this, "📅 Tüm aktiviteler görüntüleniyor...", Toast.LENGTH_SHORT).show()
            // TODO: Aktivite listesi ekranını aç
        }
    }
}