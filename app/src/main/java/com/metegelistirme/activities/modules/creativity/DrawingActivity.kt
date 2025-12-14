package com.metegelistirme.activities.modules.creativity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityDrawingBinding

class DrawingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawingBinding
    private var currentColorIndex = 0
    
    private val colors = listOf(
        "Kırmızı" to "#FF0000",
        "Mavi" to "#0000FF",
        "Yeşil" to "#00FF00",
        "Sarı" to "#FFFF00",
        "Turuncu" to "#FFA500",
        "Mor" to "#800080",
        "Siyah" to "#000000"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Boyama Zamanı"

        setupDrawing()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupDrawing() {
        binding.tvInstruction.text = "Rengini seç ve çizmeye başla! 🎨"
        
        binding.btnRed.setOnClickListener { selectColor(0) }
        binding.btnBlue.setOnClickListener { selectColor(1) }
        binding.btnGreen.setOnClickListener { selectColor(2) }
        binding.btnYellow.setOnClickListener { selectColor(3) }
        binding.btnOrange.setOnClickListener { selectColor(4) }
        binding.btnPurple.setOnClickListener { selectColor(5) }
        binding.btnBlack.setOnClickListener { selectColor(6) }
        
        binding.btnClear.setOnClickListener {
            Toast.makeText(this, "🗑️ Temizlendi!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectColor(index: Int) {
        currentColorIndex = index
        val colorName = colors[index].first
        Toast.makeText(this, "$colorName seçildi! 🎨", Toast.LENGTH_SHORT).show()
        binding.tvSelectedColor.text = "Seçili: $colorName"
    }
}
