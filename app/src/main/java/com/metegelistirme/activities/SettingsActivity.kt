package com.metegelistirme.activities

import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.metegelistirme.R

class SettingsActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        
        // Toolbar setup
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupSettings()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupSettings() {
        // Ses Ayarları
        val switchSound = findViewById<SwitchMaterial>(R.id.switchSound)
        val switchMusic = findViewById<SwitchMaterial>(R.id.switchMusic)
        val sliderVolume = findViewById<Slider>(R.id.sliderVolume)

        switchSound.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Ses efektleri: ${if(isChecked) "Açık" else "Kapalı"}", Toast.LENGTH_SHORT).show()
        }

        switchMusic.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Müzik: ${if(isChecked) "Açık" else "Kapalı"}", Toast.LENGTH_SHORT).show()
        }

        sliderVolume.addOnChangeListener { _, value, _ ->
            // Ses seviyesi değiştiğinde
        }

        // Tema Ayarları
        val radioGroupTheme = findViewById<RadioGroup>(R.id.radioGroupTheme)
        radioGroupTheme.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.radioLight -> Toast.makeText(this, "Aydınlık tema seçildi", Toast.LENGTH_SHORT).show()
                R.id.radioDark -> Toast.makeText(this, "Karanlık tema seçildi", Toast.LENGTH_SHORT).show()
                R.id.radioAuto -> Toast.makeText(this, "Otomatik tema seçildi", Toast.LENGTH_SHORT).show()
            }
        }

        // Dil Ayarları
        findViewById<MaterialButton>(R.id.btnLanguage).setOnClickListener {
            Toast.makeText(this, "Dil seçimi açılıyor...", Toast.LENGTH_SHORT).show()
            // TODO: Dil seçim dialogu göster
        }

        // Bildirim Ayarları
        val switchNotifications = findViewById<SwitchMaterial>(R.id.switchNotifications)
        val switchReminders = findViewById<SwitchMaterial>(R.id.switchReminders)

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Bildirimler: ${if(isChecked) "Açık" else "Kapalı"}", Toast.LENGTH_SHORT).show()
        }

        switchReminders.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, "Hatırlatmalar: ${if(isChecked) "Açık" else "Kapalı"}", Toast.LENGTH_SHORT).show()
        }

        // Veri Yönetimi
        findViewById<MaterialButton>(R.id.btnClearCache).setOnClickListener {
            Toast.makeText(this, "Önbellek temizlendi!", Toast.LENGTH_SHORT).show()
            // TODO: Gerçek önbellek temizleme
        }

        findViewById<MaterialButton>(R.id.btnResetProgress).setOnClickListener {
            Toast.makeText(this, "İlerleme sıfırlandı!", Toast.LENGTH_SHORT).show()
            // TODO: İlerleme sıfırlama dialogu göster
        }
    }
}