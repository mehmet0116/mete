package com.metegelistirme.activities.games

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityMemoryGameBinding

class MemoryGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoryGameBinding

    private val items = listOf("🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼")
    private val gameCards = mutableListOf<String>()

    private var firstCard: Pair<MaterialCardView, TextView>? = null
    private var secondCard: Pair<MaterialCardView, TextView>? = null

    private var score = 0
    private var matches = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoryGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Hafıza Oyunu"

        setupGame()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupGame() {
        gameCards.clear()
        gameCards.addAll(items)
        gameCards.addAll(items)
        gameCards.shuffle()

        binding.gridLayout.removeAllViews()

        gameCards.forEach { item ->
            val cardView = layoutInflater.inflate(R.layout.item_matching_card, binding.gridLayout, false) as MaterialCardView
            val cardText = cardView.findViewById<TextView>(R.id.cardText)

            cardText.text = "?"
            cardView.tag = item

            cardView.setOnClickListener {
                onCardClick(Pair(cardView, cardText))
            }

            binding.gridLayout.addView(cardView)
        }

        updateScore()
    }

    private fun onCardClick(card: Pair<MaterialCardView, TextView>) {
        val cardView = card.first
        val cardText = card.second

        if (secondCard != null || cardView.visibility == View.INVISIBLE || card == firstCard) {
            return
        }

        // Reveal card
        cardText.text = cardView.tag as String
        cardText.textSize = 36f

        if (firstCard == null) {
            firstCard = card
        } else {
            secondCard = card
            attempts++

            Handler(Looper.getMainLooper()).postDelayed({
                checkMatch()
            }, 800)
        }
    }

    private fun checkMatch() {
        val first = firstCard
        val second = secondCard

        if (first != null && second != null) {
            if (first.first.tag == second.first.tag) {
                // Match found
                score += 15
                matches++
                Toast.makeText(this, "🎉 Muhteşem! +15 puan!", Toast.LENGTH_SHORT).show()

                first.first.visibility = View.INVISIBLE
                second.first.visibility = View.INVISIBLE

                if (matches == items.size) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val bonus = if (attempts <= items.size + 2) 50 else 0
                        score += bonus
                        val message = if (bonus > 0) {
                            "🏆 Mükemmel! Az denemede bitirdin! Bonus: $bonus puan\nToplam: $score puan"
                        } else {
                            "🏆 Tebrikler! Oyunu bitirdin! Toplam: $score puan"
                        }
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }, 300)
                }
            } else {
                // No match
                first.second.text = "?"
                first.second.textSize = 24f
                second.second.text = "?"
                second.second.textSize = 24f
            }
        }
        
        firstCard = null
        secondCard = null
        updateScore()
    }

    private fun updateScore() {
        binding.tvScore.text = "Puan: $score"
        binding.tvMatches.text = "Eşleşme: $matches/${items.size}"
        binding.tvAttempts?.text = "Deneme: $attempts"
    }
}
