package com.metegelistirme.activities.modules.lifeskills

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityColorsBinding

class ColorsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityColorsBinding
    private var currentColorIndex = 0
    
    data class Color(val name: String, val emoji: String, val colorCode: String)
    
    private val colors = listOf(
        Color("Kırmızı", "❤️", "#FF0000"),
        Color("Mavi", "💙", "#0000FF"),
        Color("Yeşil", "💚", "#00FF00"),
        Color("Sarı", "💛", "#FFFF00"),
        Color("Turuncu", "🧡", "#FFA500"),
        Color("Mor", "💜", "#800080"),
        Color("Pembe", "🩷", "#FFC0CB"),
        Color("Kahverengi", "🤎", "#8B4513"),
        Color("Siyah", "🖤", "#000000"),
        Color("Beyaz", "🤍", "#FFFFFF")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityColorsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Renkler"

        showCurrentColor()
        
        binding.btnPrevious.setOnClickListener {
            if (currentColorIndex > 0) {
                currentColorIndex--
                showCurrentColor()
            }
        }
        
        binding.btnNext.setOnClickListener {
            if (currentColorIndex < colors.size - 1) {
                currentColorIndex++
                showCurrentColor()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showCurrentColor() {
        val color = colors[currentColorIndex]
        binding.tvColorEmoji.text = color.emoji
        binding.tvColorName.text = color.name
        binding.tvProgress.text = "${currentColorIndex + 1} / ${colors.size}"
        
        try {
            binding.colorPreview.setBackgroundColor(android.graphics.Color.parseColor(color.colorCode))
        } catch (e: Exception) {
            // Fallback if color parsing fails
        }
        
        binding.btnPrevious.isEnabled = currentColorIndex > 0
        binding.btnNext.isEnabled = currentColorIndex < colors.size - 1
    }
}
