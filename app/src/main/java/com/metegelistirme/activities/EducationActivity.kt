package com.metegelistirme.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.metegelistirme.R
import com.metegelistirme.activities.modules.LanguageModuleActivity
import com.metegelistirme.adapters.EducationAdapter
import com.metegelistirme.databinding.ActivityEducationBinding
import com.metegelistirme.models.EducationModule
import com.metegelistirme.models.ModuleType

class EducationActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityEducationBinding
    private lateinit var buttonClickSound: MediaPlayer
    private lateinit var educationAdapter: EducationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupRecyclerView()
    }
    
    private fun setupUI() {
        binding.toolbar.title = getString(R.string.education_title)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    private fun setupRecyclerView() {
        val educationModules = getEducationModules()
        educationAdapter = EducationAdapter(educationModules) { module ->
            playButtonClickSound()
            navigateToModule(module)
        }

        binding.educationRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@EducationActivity)
            adapter = educationAdapter
        }
    }

    private fun getEducationModules(): List<EducationModule> {
        return listOf(
            EducationModule(
                id = 1,
                title = getString(R.string.language_module),
                description = "Sözcük öğrenme ve telaffuz",
                iconResId = R.drawable.ic_education,
                backgroundColor = R.color.primary_color,
                moduleType = ModuleType.LANGUAGE
            ),
            EducationModule(
                id = 2,
                title = getString(R.string.math_module),
                description = "Sayılar ve temel matematik",
                iconResId = R.drawable.ic_education,
                backgroundColor = R.color.secondary_color,
                moduleType = ModuleType.MATH
            ),
            EducationModule(
                id = 3,
                title = getString(R.string.cognitive_module),
                description = "Dikkat ve düşünce geliştirme",
                iconResId = R.drawable.ic_education,
                backgroundColor = R.color.accent_color,
                moduleType = ModuleType.COGNITIVE
            ),
            EducationModule(
                id = 4,
                title = getString(R.string.creativity_module),
                description = "Yaratıcı düşünce ve sannat",
                iconResId = R.drawable.ic_education,
                backgroundColor = R.color.success_green,
                moduleType = ModuleType.CREATIVITY
            ),
            EducationModule(
                id = 5,
                title = getString(R.string.life_skills_module),
                description = "Günlük yaşam beceri",
                iconResId = R.drawable.ic_education,
                backgroundColor = R.color.warning_orange,
                moduleType = ModuleType.LIFE_SKILLS
            )
        )
    }

    private fun navigateToModule(module: EducationModule) {
        val intent = when (module.moduleType) {
            ModuleType.LANGUAGE -> Intent(this, LanguageModuleActivity::class.java)
            ModuleType.MATH -> {
                showComingSoonMessage()
                return
            }
            ModuleType.COGNITIVE -> {
                showComingSoonMessage()
                return
            }
            ModuleType.CREATIVITY -> {
                showComingSoonMessage()
                return
            }
            ModuleType.LIFE_SKILLS -> {
                showComingSoonMessage()
                return
            }
        }
        startActivity(intent)
    }
    
    private fun playButtonClickSound() {
        try {
            // Ses dosyası varsa çal (R.raw.button_click)
            val soundResId = resources.getIdentifier("button_click", "raw", packageName)
            if (soundResId != 0) {
                buttonClickSound = MediaPlayer.create(this, soundResId)
                buttonClickSound.setOnCompletionListener {
                    it.release()
                }
                buttonClickSound.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Ses dosyası yoksa sessiz devam et
        }
    }
    
    private fun showComingSoonMessage() {
        android.app.AlertDialog.Builder(this)
            .setTitle("Yakında")
            .setMessage("Bu modül çok yakında eklenecek!")
            .setPositiveButton("Tamam", null)
            .show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (::buttonClickSound.isInitialized && buttonClickSound.isPlaying) {
            buttonClickSound.stop()
            buttonClickSound.release()
        }
    }
}