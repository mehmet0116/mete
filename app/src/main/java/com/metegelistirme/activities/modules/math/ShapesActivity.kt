package com.metegelistirme.activities.modules.math

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityShapesBinding

class ShapesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShapesBinding
    private var currentShapeIndex = 0
    
    data class Shape(val name: String, val emoji: String, val description: String)
    
    private val shapes = listOf(
        Shape("Daire", "⭕", "Daire yuvarladır, köşesi yoktur"),
        Shape("Kare", "🟦", "Karenin 4 eşit kenarı vardır"),
        Shape("Üçgen", "🔺", "Üçgenin 3 köşesi vardır"),
        Shape("Dikdörtgen", "▬", "Dikdörtgenin 4 köşesi vardır"),
        Shape("Yıldız", "⭐", "Yıldız sivri uçludur"),
        Shape("Kalp", "❤️", "Kalp sevgiyi gösterir"),
        Shape("Altıgen", "⬡", "Altıgenin 6 köşesi vardır"),
        Shape("Oval", "🥚", "Oval yumurta şeklindedir")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShapesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Şekiller"

        showCurrentShape()
        
        binding.btnPrevious.setOnClickListener {
            if (currentShapeIndex > 0) {
                currentShapeIndex--
                showCurrentShape()
            }
        }
        
        binding.btnNext.setOnClickListener {
            if (currentShapeIndex < shapes.size - 1) {
                currentShapeIndex++
                showCurrentShape()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showCurrentShape() {
        val shape = shapes[currentShapeIndex]
        binding.tvShapeEmoji.text = shape.emoji
        binding.tvShapeName.text = shape.name
        binding.tvShapeDescription.text = shape.description
        binding.tvProgress.text = "${currentShapeIndex + 1} / ${shapes.size}"
        
        binding.btnPrevious.isEnabled = currentShapeIndex > 0
        binding.btnNext.isEnabled = currentShapeIndex < shapes.size - 1
    }
}
