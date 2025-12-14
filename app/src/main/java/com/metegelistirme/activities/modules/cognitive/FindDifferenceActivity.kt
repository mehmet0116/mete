package com.metegelistirme.activities.modules.cognitive

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityFindDifferenceBinding

class FindDifferenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindDifferenceBinding
    private var currentPuzzle = 0
    private var score = 0

    data class Puzzle(val emoji1: String, val emoji2: String, val different: String, val correctAnswer: Int)

    private val puzzles = listOf(
        Puzzle("🍎🍎🍎🍊", "🍎🍎🍎🍎", "🍊", 3),
        Puzzle("🐶🐶🐱🐶", "🐶🐶🐶🐶", "🐱", 2),
        Puzzle("⭐⭐⭐⭐", "⭐⭐🌟⭐", "🌟", 2),
        Puzzle("🔵🔵🔴🔵", "🔵🔵🔵🔵", "🔴", 2),
        Puzzle("😊😊😊😢", "😊😊😊😊", "😢", 3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindDifferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Farkı Bul"

        showPuzzle()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showPuzzle() {
        if (currentPuzzle >= puzzles.size) {
            showResults()
            return
        }

        val puzzle = puzzles[currentPuzzle]
        binding.tvPuzzle.text = puzzle.emoji1
        binding.tvQuestion.text = "Farklı olanı bul: ${puzzle.different}"
        binding.tvScore.text = "Puan: $score"
        binding.tvProgress.text = "Soru: ${currentPuzzle + 1}/${puzzles.size}"

        val buttons = listOf(
            binding.btnAnswer1, binding.btnAnswer2,
            binding.btnAnswer3, binding.btnAnswer4
        )

        puzzle.emoji1.forEachIndexed { index, char ->
            if (index < buttons.size) {
                buttons[index].text = char.toString()
                buttons[index].setOnClickListener {
                    checkAnswer(index)
                }
            }
        }
    }

    private fun checkAnswer(selected: Int) {
        val puzzle = puzzles[currentPuzzle]
        if (selected == puzzle.correctAnswer) {
            score += 10
            Toast.makeText(this, "✅ Doğru! +10 puan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Yanlış! Tekrar dene", Toast.LENGTH_SHORT).show()
        }
        
        currentPuzzle++
        binding.root.postDelayed({ showPuzzle() }, 1000)
    }

    private fun showResults() {
        binding.tvPuzzle.text = "🎉"
        binding.tvQuestion.text = "Tebrikler!"
        binding.tvProgress.text = "Toplam Puan: $score"
        binding.btnAnswer1.visibility = Button.GONE
        binding.btnAnswer2.visibility = Button.GONE
        binding.btnAnswer3.visibility = Button.GONE
        binding.btnAnswer4.visibility = Button.GONE
    }
}
