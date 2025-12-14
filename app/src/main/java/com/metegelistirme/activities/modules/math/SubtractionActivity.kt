package com.metegelistirme.activities.modules.math

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityMathQuizBinding
import kotlin.random.Random

class SubtractionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMathQuizBinding
    private var score = 0
    private var questionsAnswered = 0
    private val totalQuestions = 10
    
    private var num1 = 0
    private var num2 = 0
    private var correctAnswer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMathQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Çıkarma Oyunu"

        generateQuestion()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun generateQuestion() {
        if (questionsAnswered >= totalQuestions) {
            showResults()
            return
        }

        num1 = Random.nextInt(5, 15)
        num2 = Random.nextInt(1, num1)
        correctAnswer = num1 - num2

        binding.tvQuestion.text = "$num1 - $num2 = ?"
        binding.tvScore.text = "Puan: $score"
        binding.tvProgress.text = "Soru: ${questionsAnswered + 1}/$totalQuestions"

        val wrongAnswer1 = (correctAnswer + Random.nextInt(1, 4)).coerceAtMost(num1)
        val wrongAnswer2 = (correctAnswer - Random.nextInt(1, 4)).coerceAtLeast(0)
        val answers = listOf(correctAnswer, wrongAnswer1, wrongAnswer2).shuffled()

        binding.btnAnswer1.text = answers[0].toString()
        binding.btnAnswer2.text = answers[1].toString()
        binding.btnAnswer3.text = answers[2].toString()

        binding.btnAnswer1.setOnClickListener { checkAnswer(answers[0]) }
        binding.btnAnswer2.setOnClickListener { checkAnswer(answers[1]) }
        binding.btnAnswer3.setOnClickListener { checkAnswer(answers[2]) }
    }

    private fun checkAnswer(answer: Int) {
        if (answer == correctAnswer) {
            score += 10
            Toast.makeText(this, "✅ Doğru! +10 puan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Yanlış! Doğru cevap: $correctAnswer", Toast.LENGTH_SHORT).show()
        }
        
        questionsAnswered++
        binding.root.postDelayed({ generateQuestion() }, 1000)
    }

    private fun showResults() {
        binding.tvQuestion.text = "🎉 Çıkarma Tamamlandı!"
        binding.tvProgress.text = "Toplam Puan: $score / ${totalQuestions * 10}"
        binding.btnAnswer1.visibility = Button.GONE
        binding.btnAnswer2.visibility = Button.GONE
        binding.btnAnswer3.visibility = Button.GONE
    }
}
