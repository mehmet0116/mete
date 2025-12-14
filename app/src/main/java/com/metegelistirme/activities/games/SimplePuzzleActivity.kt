package com.metegelistirme.activities.games

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R

class SimplePuzzleActivity : AppCompatActivity() {

    private var score = 0
    private var currentPuzzle = 0

    private val puzzles = listOf(
        Pair("2 + 2 = ?", "4"),
        Pair("Kaç göz var?", "2"),
        Pair("Elma + Elma = ?", "2 Elma"),
        Pair("Kedi ne der?", "Miyav")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_puzzle)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupPuzzle()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupPuzzle() {
        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val btn1 = findViewById<Button>(R.id.btnAnswer1)
        val btn2 = findViewById<Button>(R.id.btnAnswer2)
        val btn3 = findViewById<Button>(R.id.btnAnswer3)

        if (currentPuzzle >= puzzles.size) {
            tvQuestion.text = "🎉 Tebrikler! Tüm soruları çözdün!"
            tvScore.text = "Toplam Puan: $score"
            btn1.visibility = Button.GONE
            btn2.visibility = Button.GONE
            btn3.visibility = Button.GONE
            return
        }

        val (question, correctAnswer) = puzzles[currentPuzzle]
        tvQuestion.text = question
        tvScore.text = "Puan: $score"

        // Cevap butonları
        val answers = listOf(correctAnswer, "Yanlış 1", "Yanlış 2").shuffled()

        btn1.text = answers[0]
        btn2.text = answers[1]
        btn3.text = answers[2]

        btn1.setOnClickListener { checkAnswer(btn1.text.toString(), correctAnswer) }
        btn2.setOnClickListener { checkAnswer(btn2.text.toString(), correctAnswer) }
        btn3.setOnClickListener { checkAnswer(btn3.text.toString(), correctAnswer) }
    }

    private fun checkAnswer(selected: String, correct: String) {
        if (selected == correct) {
            score += 10
            Toast.makeText(this, "✅ Doğru! +10 puan", Toast.LENGTH_SHORT).show()
            currentPuzzle++
            setupPuzzle()
        } else {
            Toast.makeText(this, "❌ Yanlış! Tekrar dene", Toast.LENGTH_SHORT).show()
        }
    }
}
