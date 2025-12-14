package com.metegelistirme.activities.modules.math

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.metegelistirme.R
import com.metegelistirme.databinding.ActivityCountingBinding

class CountingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountingBinding
    private var currentNumber = 1
    private val maxNumber = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Sayı Öğreniyorum"

        updateDisplay()

        binding.btnPrevious.setOnClickListener {
            if (currentNumber > 1) {
                currentNumber--
                updateDisplay()
            }
        }

        binding.btnNext.setOnClickListener {
            if (currentNumber < maxNumber) {
                currentNumber++
                updateDisplay()
            }
        }

        // Number buttons 1-10
        setupNumberButtons()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupNumberButtons() {
        val buttonIds = listOf(
            R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn10
        )

        buttonIds.forEachIndexed { index, buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                currentNumber = index + 1
                updateDisplay()
                Toast.makeText(this, "Sayı: ${index + 1}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateDisplay() {
        binding.tvCurrentNumber.text = currentNumber.toString()
        binding.tvNumberWord.text = getNumberWord(currentNumber)
        binding.tvVisual.text = getVisualRepresentation(currentNumber)

        binding.btnPrevious.isEnabled = currentNumber > 1
        binding.btnNext.isEnabled = currentNumber < maxNumber
    }

    private fun getNumberWord(num: Int): String {
        return when (num) {
            1 -> "Bir"
            2 -> "İki"
            3 -> "Üç"
            4 -> "Dört"
            5 -> "Beş"
            6 -> "Altı"
            7 -> "Yedi"
            8 -> "Sekiz"
            9 -> "Dokuz"
            10 -> "On"
            11 -> "On Bir"
            12 -> "On İki"
            13 -> "On Üç"
            14 -> "On Dört"
            15 -> "On Beş"
            16 -> "On Altı"
            17 -> "On Yedi"
            18 -> "On Sekiz"
            19 -> "On Dokuz"
            20 -> "Yirmi"
            else -> num.toString()
        }
    }

    private fun getVisualRepresentation(num: Int): String {
        return "⭐".repeat(minOf(num, 10))
    }
}
