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
            showDetailedReport()
        }

        val allActivitiesButton = findViewById<MaterialButton>(R.id.btnAllActivities)
        allActivitiesButton?.setOnClickListener {
            showAllActivities()
        }
    }

    private fun showDetailedReport() {
        val report = """
            📊 Detaylı İlerleme Raporu
            
            🎮 Oyunlar:
            • Eşleştirme Oyunu: 15 kez oynandı
            • Bulmaca Oyunu: 12 kez oynandı
            • Hafıza Oyunu: 8 kez oynandı
            • Quiz Oyunu: 10 kez oynandı
            
            📚 Eğitim Modülleri:
            • Dil Gelişimi: %75 tamamlandı
            • Matematik: %60 tamamlandı
            • Bilişsel Gelişim: %85 tamamlandı
            • Günlük Yaşam: %70 tamamlandı
            
            ⭐ Toplam Puan: 850
            🎯 Seviye: 5
        """.trimIndent()
        
        android.app.AlertDialog.Builder(this)
            .setTitle("Detaylı Rapor")
            .setMessage(report)
            .setPositiveButton("Tamam", null)
            .show()
    }

    private fun showAllActivities() {
        val activities = """
            📅 Son Aktiviteler
            
            Bugün:
            • 10:30 - Eşleştirme Oyunu (15 dk)
            • 11:00 - Alfabe Öğreniyorum (20 dk)
            • 14:30 - Toplama Oyunu (10 dk)
            
            Dün:
            • 09:15 - Hafıza Kartları (12 dk)
            • 10:00 - Renkler (15 dk)
            • 15:30 - Quiz Oyunu (18 dk)
            
            2 gün önce:
            • 10:00 - Hayvanlar (20 dk)
            • 11:30 - Sayı Öğreniyorum (15 dk)
        """.trimIndent()
        
        android.app.AlertDialog.Builder(this)
            .setTitle("Tüm Aktiviteler")
            .setMessage(activities)
            .setPositiveButton("Tamam", null)
            .show()
    }
}