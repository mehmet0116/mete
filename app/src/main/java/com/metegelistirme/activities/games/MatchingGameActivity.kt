package com.metegelistirme.activities.games

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.databinding.ActivityMatchingGameBinding
import com.metegelistirme.databinding.ItemGameCardBinding

class MatchingGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchingGameBinding

    private val items = listOf("🍎", "🍌", "🍇", "🍓", "🍊", "🍉")
    private val gameCards = mutableListOf<String>()

    private var firstCardBinding: ItemGameCardBinding? = null
    private var secondCardBinding: ItemGameCardBinding? = null

    private var score = 0
    private var matches = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchingGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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
            val cardBinding = ItemGameCardBinding.inflate(layoutInflater, binding.gridLayout, false)

            cardBinding.cardText.text = "?"
            cardBinding.root.tag = item // Store the emoji in the root view's tag

            cardBinding.root.setOnClickListener {
                onCardClick(cardBinding)
            }

            binding.gridLayout.addView(cardBinding.root)
        }

        updateScore()
    }

    private fun onCardClick(cardBinding: ItemGameCardBinding) {
        // If a check is pending, or card is matched, or it's the first card again, do nothing
        if (secondCardBinding != null || cardBinding.root.visibility == View.INVISIBLE || cardBinding == firstCardBinding) {
            return
        }

        // Reveal card
        cardBinding.cardText.text = cardBinding.root.tag as String
        cardBinding.cardText.textSize = 36f

        if (firstCardBinding == null) {
            firstCardBinding = cardBinding
        } else { // secondCardBinding is guaranteed to be null here
            secondCardBinding = cardBinding

            Handler(Looper.getMainLooper()).postDelayed({
                checkMatch()
            }, 500)
        }
    }

    private fun checkMatch() {
        val first = firstCardBinding
        val second = secondCardBinding

        if (first?.root?.tag == second?.root?.tag) {
            // Match
            score += 10
            matches++
            Toast.makeText(this, "🎉 Harika! +10 puan!", Toast.LENGTH_SHORT).show()

            first?.root?.visibility = View.INVISIBLE
            second?.root?.visibility = View.INVISIBLE

            if (matches == items.size) {
                Handler(Looper.getMainLooper()).postDelayed({
                    Toast.makeText(this, "🏆 Tebrikler! Oyunu bitirdin! Toplam: $score puan", Toast.LENGTH_LONG).show()
                }, 300)
            }
        } else {
            // No match
            first?.cardText?.text = "?"
            first?.cardText?.textSize = 24f
            second?.cardText?.text = "?"
            second?.cardText?.textSize = 24f
        }
        
        firstCardBinding = null
        secondCardBinding = null
        updateScore()
    }

    private fun updateScore() {
        binding.tvScore.text = "Puan: $score"
        binding.tvMatches.text = "Eşleşme: $matches/${items.size}"
    }
}
