package com.metegelistirme.activities.modules.lifeskills

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityAnimalsBinding

class AnimalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalsBinding
    private var currentAnimalIndex = 0
    
    data class Animal(val name: String, val emoji: String, val sound: String)
    
    private val animals = listOf(
        Animal("Köpek", "🐶", "Hav Hav!"),
        Animal("Kedi", "🐱", "Miyav!"),
        Animal("İnek", "🐄", "Möö!"),
        Animal("Koyun", "🐑", "Meee!"),
        Animal("Kuş", "🐦", "Cik Cik!"),
        Animal("Aslan", "🦁", "Roar!"),
        Animal("Fil", "🐘", "Tüüüt!"),
        Animal("At", "🐴", "İhahaha!"),
        Animal("Tavuk", "🐔", "Gıt Gıt!"),
        Animal("Kurbağa", "🐸", "Vrak Vrak!")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Hayvanlar"

        showCurrentAnimal()
        
        binding.btnPrevious.setOnClickListener {
            if (currentAnimalIndex > 0) {
                currentAnimalIndex--
                showCurrentAnimal()
            }
        }
        
        binding.btnNext.setOnClickListener {
            if (currentAnimalIndex < animals.size - 1) {
                currentAnimalIndex++
                showCurrentAnimal()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showCurrentAnimal() {
        val animal = animals[currentAnimalIndex]
        binding.tvAnimalEmoji.text = animal.emoji
        binding.tvAnimalName.text = animal.name
        binding.tvAnimalSound.text = animal.sound
        binding.tvProgress.text = "${currentAnimalIndex + 1} / ${animals.size}"
        
        binding.btnPrevious.isEnabled = currentAnimalIndex > 0
        binding.btnNext.isEnabled = currentAnimalIndex < animals.size - 1
    }
}
