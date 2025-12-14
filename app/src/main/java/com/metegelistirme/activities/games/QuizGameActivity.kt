package com.metegelistirme.activities.games

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityQuizGameBinding

class QuizGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizGameBinding
    private var score = 0
    private var currentQuestion = 0

    data class QuizQuestion(
        val question: String,
        val correctAnswer: String,
        val wrongAnswers: List<String>,
        val category: String
    )

    private val quizQuestions = listOf(
        QuizQuestion(
            "Kaç parmağın var?",
            "10",
            listOf("5", "8", "12"),
            "Sayılar"
        ),
        QuizQuestion(
            "Kırmızı ile sarı karıştırılırsa ne olur?",
            "Turuncu",
            listOf("Mor", "Yeşil", "Kahverengi"),
            "Renkler"
        ),
        QuizQuestion(
            "Hangi hayvan havlar?",
            "Köpek",
            listOf("Kedi", "Kuş", "Balık"),
            "Hayvanlar"
        ),
        QuizQuestion(
            "Hangi şekil 4 köşelidir?",
            "Kare",
            listOf("Üçgen", "Daire", "Yıldız"),
            "Şekiller"
        ),
        QuizQuestion(
            "3 + 2 = ?",
            "5",
            listOf("4", "6", "7"),
            "Matematik"
        ),
        QuizQuestion(
            "Hangi renk gökyüzünün rengidir?",
            "Mavi",
            listOf("Yeşil", "Kırmızı", "Sarı"),
            "Renkler"
        ),
        QuizQuestion(
            "Hangi meyve kırmızıdır?",
            "Elma",
            listOf("Muz", "Portakal", "Üzüm"),
            "Meyveler"
        ),
        QuizQuestion(
            "5 - 2 = ?",
            "3",
            listOf("2", "4", "7"),
            "Matematik"
        ),
        QuizQuestion(
            "Hangi hayvan miyavlar?",
            "Kedi",
            listOf("Köpek", "Inek", "At"),
            "Hayvanlar"
        ),
        QuizQuestion(
            "Kaç mevsim vardır?",
            "4",
            listOf("2", "3", "5"),
            "Genel Bilgi"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Sesli Quiz"

        loadQuestion()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadQuestion() {
        if (currentQuestion >= quizQuestions.size) {
            showResults()
            return
        }

        val question = quizQuestions[currentQuestion]
        binding.tvCategory.text = "📚 ${question.category}"
        binding.tvQuestion.text = question.question
        binding.tvScore.text = "Puan: $score"
        binding.tvProgress.text = "Soru: ${currentQuestion + 1}/${quizQuestions.size}"

        // Shuffle all answers
        val allAnswers = (question.wrongAnswers + question.correctAnswer).shuffled()

        binding.btnAnswer1.text = allAnswers[0]
        binding.btnAnswer2.text = allAnswers[1]
        binding.btnAnswer3.text = allAnswers[2]
        binding.btnAnswer4.text = allAnswers[3]

        // Set click listeners
        binding.btnAnswer1.setOnClickListener { checkAnswer(binding.btnAnswer1.text.toString(), question.correctAnswer) }
        binding.btnAnswer2.setOnClickListener { checkAnswer(binding.btnAnswer2.text.toString(), question.correctAnswer) }
        binding.btnAnswer3.setOnClickListener { checkAnswer(binding.btnAnswer3.text.toString(), question.correctAnswer) }
        binding.btnAnswer4.setOnClickListener { checkAnswer(binding.btnAnswer4.text.toString(), question.correctAnswer) }

        // Enable all buttons
        binding.btnAnswer1.isEnabled = true
        binding.btnAnswer2.isEnabled = true
        binding.btnAnswer3.isEnabled = true
        binding.btnAnswer4.isEnabled = true
    }

    private fun checkAnswer(selected: String, correct: String) {
        // Disable all buttons to prevent multiple clicks
        binding.btnAnswer1.isEnabled = false
        binding.btnAnswer2.isEnabled = false
        binding.btnAnswer3.isEnabled = false
        binding.btnAnswer4.isEnabled = false

        if (selected == correct) {
            score += 10
            Toast.makeText(this, "✅ Harika! Doğru cevap! +10 puan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "❌ Yanlış! Doğru cevap: $correct", Toast.LENGTH_SHORT).show()
        }

        // Move to next question after a short delay
        binding.root.postDelayed({
            currentQuestion++
            loadQuestion()
        }, 1500)
    }

    private fun showResults() {
        binding.tvCategory.text = "🎉 Quiz Tamamlandı!"
        binding.tvQuestion.text = "Toplam Puanın: $score / ${quizQuestions.size * 10}"
        binding.tvProgress.text = ""

        val percentage = (score * 100) / (quizQuestions.size * 10)
        val message = when {
            percentage >= 90 -> "🏆 Mükemmel! Harikasın!"
            percentage >= 70 -> "🌟 Çok iyi! Aferin!"
            percentage >= 50 -> "👍 İyi! Biraz daha çalış!"
            else -> "💪 Tekrar dene! Başarabilirsin!"
        }

        binding.btnAnswer1.text = message
        binding.btnAnswer2.visibility = Button.GONE
        binding.btnAnswer3.visibility = Button.GONE
        binding.btnAnswer4.visibility = Button.GONE

        binding.btnAnswer1.isEnabled = false
    }
}
