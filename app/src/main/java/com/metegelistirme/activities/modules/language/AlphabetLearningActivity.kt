package com.metegelistirme.activities.modules.language

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityAlphabetLearningBinding

class AlphabetLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlphabetLearningBinding
    private val alphabet = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ".toList()
    private var currentLetterIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Alfabe Öğreniyorum"

        setupAlphabet()
        showCurrentLetter()
        
        binding.btnPrevious.setOnClickListener {
            if (currentLetterIndex > 0) {
                currentLetterIndex--
                showCurrentLetter()
            }
        }
        
        binding.btnNext.setOnClickListener {
            if (currentLetterIndex < alphabet.size - 1) {
                currentLetterIndex++
                showCurrentLetter()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupAlphabet() {
        binding.alphabetGrid.removeAllViews()
        
        alphabet.forEach { letter ->
            val cardView = layoutInflater.inflate(R.layout.item_alphabet_card, binding.alphabetGrid, false) as MaterialCardView
            val letterText = cardView.findViewById<TextView>(R.id.tvLetter)
            
            letterText.text = letter.toString()
            cardView.setOnClickListener {
                currentLetterIndex = alphabet.indexOf(letter)
                showCurrentLetter()
                Toast.makeText(this, "Harf: $letter", Toast.LENGTH_SHORT).show()
            }
            
            binding.alphabetGrid.addView(cardView)
        }
    }

    private fun showCurrentLetter() {
        val letter = alphabet[currentLetterIndex]
        binding.tvCurrentLetter.text = letter.toString()
        binding.tvLetterInfo.text = "Harf: $letter (${currentLetterIndex + 1}/${alphabet.size})"
        
        // Sample words for each letter
        val sampleWord = getSampleWord(letter)
        binding.tvSampleWord.text = "Örnek: $sampleWord"
        
        binding.btnPrevious.isEnabled = currentLetterIndex > 0
        binding.btnNext.isEnabled = currentLetterIndex < alphabet.size - 1
    }

    private fun getSampleWord(letter: Char): String {
        return when (letter) {
            'A' -> "🍎 Elma"
            'B' -> "🐟 Balık"
            'C' -> "👶 Çocuk"
            'Ç' -> "🌸 Çiçek"
            'D' -> "📓 Defter"
            'E' -> "🏠 Ev"
            'F' -> "🐘 Fil"
            'G' -> "🌹 Gül"
            'Ğ' -> "🏔️ Dağ"
            'H' -> "🥒 Hıyar"
            'I' -> "💡 Işık"
            'İ' -> "🐄 İnek"
            'J' -> "🎲 Jilet"
            'K' -> "🐑 Koyun"
            'L' -> "🍋 Limon"
            'M' -> "🍌 Muz"
            'N' -> "🍐 Nar"
            'O' -> "🏫 Okul"
            'Ö' -> "🦆 Ördek"
            'P' -> "🍕 Pizza"
            'R' -> "🎨 Resim"
            'S' -> "⏰ Saat"
            'Ş' -> "🌂 Şemsiye"
            'T' -> "🐰 Tavşan"
            'U' -> "✈️ Uçak"
            'Ü' -> "🍇 Üzüm"
            'V' -> "🚂 Vagon"
            'Y' -> "⭐ Yıldız"
            'Z' -> "🔔 Zil"
            else -> "Örnek kelime"
        }
    }
}
