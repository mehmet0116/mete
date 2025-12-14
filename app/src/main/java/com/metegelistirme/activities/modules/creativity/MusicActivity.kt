package com.metegelistirme.activities.modules.creativity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicBinding

    data class Instrument(val name: String, val emoji: String, val sound: String)
    
    private val instruments = listOf(
        Instrument("Piyano", "🎹", "Do Re Mi Fa Sol La Si"),
        Instrument("Gitar", "🎸", "Tın Tın Tın"),
        Instrument("Davul", "🥁", "Bam Bam Bam"),
        Instrument("Trompet", "🎺", "Ta Ta Taa"),
        Instrument("Keman", "🎻", "Fı Fı Fııı")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Müzik Zamanı"

        setupMusic()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupMusic() {
        binding.tvInstruction.text = "Enstrümanına tıkla ve müzik yap! 🎵"
        
        binding.btnPiano.setOnClickListener { playInstrument(0) }
        binding.btnGuitar.setOnClickListener { playInstrument(1) }
        binding.btnDrum.setOnClickListener { playInstrument(2) }
        binding.btnTrumpet.setOnClickListener { playInstrument(3) }
        binding.btnViolin.setOnClickListener { playInstrument(4) }
    }

    private fun playInstrument(index: Int) {
        val instrument = instruments[index]
        binding.tvPlaying.text = "${instrument.emoji} ${instrument.name}"
        binding.tvSound.text = instrument.sound
        Toast.makeText(this, "${instrument.name} çalıyor! ${instrument.emoji}", Toast.LENGTH_SHORT).show()
    }
}
