package com.metegelistirme.activities.modules.cognitive

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityMemoryCardsBinding

class MemoryCardsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoryCardsBinding
    private val sequence = mutableListOf<Int>()
    private val userSequence = mutableListOf<Int>()
    private var level = 1
    private var score = 0
    private var isShowingSequence = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoryCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Hafıza Kartları"

        setupCards()
        binding.btnStart.setOnClickListener {
            startNewRound()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupCards() {
        val cards = listOf(
            binding.card1, binding.card2, binding.card3,
            binding.card4, binding.card5, binding.card6
        )

        cards.forEachIndexed { index, card ->
            card.setOnClickListener {
                if (!isShowingSequence) {
                    onCardClick(index)
                }
            }
        }
    }

    private fun startNewRound() {
        binding.btnStart.visibility = View.GONE
        isShowingSequence = true
        userSequence.clear()
        
        // Add one more step to the sequence
        sequence.add((0..5).random())
        
        showSequence()
    }

    private fun showSequence() {
        val cards = listOf(
            binding.card1, binding.card2, binding.card3,
            binding.card4, binding.card5, binding.card6
        )
        
        var delay = 0L
        sequence.forEach { index ->
            Handler(Looper.getMainLooper()).postDelayed({
                highlightCard(cards[index], true)
                Handler(Looper.getMainLooper()).postDelayed({
                    highlightCard(cards[index], false)
                }, 500)
            }, delay)
            delay += 800
        }

        Handler(Looper.getMainLooper()).postDelayed({
            isShowingSequence = false
            binding.tvInstruction.text = "Sırayı tekrarla!"
        }, delay)
    }

    private fun highlightCard(card: MaterialCardView, highlight: Boolean) {
        card.alpha = if (highlight) 1.0f else 0.5f
    }

    private fun onCardClick(index: Int) {
        userSequence.add(index)
        
        val cards = listOf(
            binding.card1, binding.card2, binding.card3,
            binding.card4, binding.card5, binding.card6
        )
        
        highlightCard(cards[index], true)
        Handler(Looper.getMainLooper()).postDelayed({
            highlightCard(cards[index], false)
        }, 300)

        // Check if user sequence matches so far
        if (userSequence.last() != sequence[userSequence.size - 1]) {
            // Wrong!
            Toast.makeText(this, "❌ Yanlış! Baştan başla", Toast.LENGTH_SHORT).show()
            resetGame()
        } else if (userSequence.size == sequence.size) {
            // Completed this level!
            score += level * 10
            level++
            updateScore()
            Toast.makeText(this, "✅ Harika! Seviye $level", Toast.LENGTH_SHORT).show()
            
            Handler(Looper.getMainLooper()).postDelayed({
                startNewRound()
            }, 1000)
        }
    }

    private fun resetGame() {
        sequence.clear()
        userSequence.clear()
        level = 1
        score = 0
        updateScore()
        binding.btnStart.visibility = View.VISIBLE
        binding.tvInstruction.text = "Başlamak için tıkla"
    }

    private fun updateScore() {
        binding.tvScore.text = "Puan: $score"
        binding.tvLevel.text = "Seviye: $level"
    }
}
