package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.metegelistirme.R
import com.metegelistirme.activities.modules.LanguageModuleActivity
import com.metegelistirme.databinding.ActivityEducationBinding
import com.metegelistirme.ui.adapters.LessonAdapter
import com.metegelistirme.ui.models.Lesson

class EducationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEducationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar setup
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val lessons = listOf(
            Lesson("language", getString(R.string.language_development), "Kelime, alfabe, cümle", R.color.primary_color),
            Lesson("math", getString(R.string.math_skills), "Sayılar, toplama, çıkarma", R.color.secondary_color),
            Lesson("cognitive", getString(R.string.cognitive_development), "Mantık ve hafıza", R.color.accent_color),
            Lesson("creativity", getString(R.string.creativity), "Resim ve müzik", R.color.success_green),
            Lesson("life", getString(R.string.life_skills), "Günlük alışkanlıklar", R.color.warning_orange)
        )

        val lessonAdapter = LessonAdapter(lessons) { lesson ->
            when (lesson.id) {
                "language" -> startActivity(Intent(this, LanguageModuleActivity::class.java))
                else -> {
                    Toast.makeText(this, "Bu modül yakında!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.educationRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@EducationActivity)
            adapter = lessonAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
