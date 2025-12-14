package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.metegelistirme.R
import com.metegelistirme.adapters.MainMenuAdapter
import com.metegelistirme.databinding.ActivityMainMenuBinding
import com.metegelistirme.models.MainMenuItem
import com.metegelistirme.viewmodels.MainMenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainMenuBinding
    private val viewModel: MainMenuViewModel by viewModels()
    private lateinit var adapter: MainMenuAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupObservers()
        loadMenuItems()
    }
    
    private fun setupUI() {
        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.welcome)
        
        // Setup RecyclerView
        adapter = MainMenuAdapter { menuItem ->
            onMenuItemClicked(menuItem)
        }
        
        binding.menuRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainMenuActivity, 2)
            adapter = this@MainMenuActivity.adapter
            setHasFixedSize(true)
        }
        
        // Setup profile section
        binding.profileSection.setOnClickListener {
            // Navigate to profile activity
            // Intent(this, ProfileActivity::class.java).also { startActivity(it) }
        }
        
        // Setup settings button
        binding.settingsButton.setOnClickListener {
            Intent(this, SettingsActivity::class.java).also { startActivity(it) }
        }
    }
    
    private fun setupObservers() {
        viewModel.menuItems.observe(this) { menuItems ->
            adapter.submitList(menuItems)
        }
        
        viewModel.userName.observe(this) { name ->
            binding.userName.text = name ?: getString(R.string.welcome)
        }
        
        viewModel.progress.observe(this) { progress ->
            binding.progressBar.progress = progress
            binding.progressText.text = getString(R.string.progress_percentage, progress)
        }
    }
    
    private fun loadMenuItems() {
        val menuItems = listOf(
            MainMenuItem(
                id = 1,
                title = getString(R.string.module_language),
                iconRes = R.drawable.ic_language,
                colorRes = R.color.edu_blue,
                destination = EducationActivity::class.java
            ),
            MainMenuItem(
                id = 2,
                title = getString(R.string.module_math),
                iconRes = R.drawable.ic_math,
                colorRes = R.color.edu_green,
                destination = EducationActivity::class.java
            ),
            MainMenuItem(
                id = 3,
                title = getString(R.string.module_cognitive),
                iconRes = R.drawable.ic_brain,
                colorRes = R.color.edu_purple,
                destination = EducationActivity::class.java
            ),
            MainMenuItem(
                id = 4,
                title = getString(R.string.module_creativity),
                iconRes = R.drawable.ic_creativity,
                colorRes = R.color.edu_yellow,
                destination = EducationActivity::class.java
            ),
            MainMenuItem(
                id = 5,
                title = getString(R.string.module_life_skills),
                iconRes = R.drawable.ic_life_skills,
                colorRes = R.color.edu_red,
                destination = EducationActivity::class.java
            ),
            MainMenuItem(
                id = 6,
                title = getString(R.string.menu_games),
                iconRes = R.drawable.ic_games,
                colorRes = R.color.accent_color,
                destination = GamesActivity::class.java
            )
        )
        
        viewModel.setMenuItems(menuItems)
    }
    
    private fun onMenuItemClicked(menuItem: MainMenuItem) {
        when (menuItem.id) {
            1, 2, 3, 4, 5 -> {
                // Education modules
                val intent = Intent(this, EducationActivity::class.java).apply {
                    putExtra("MODULE_ID", menuItem.id)
                    putExtra("MODULE_TITLE", menuItem.title)
                }
                startActivity(intent)
            }
            6 -> {
                // Games
                startActivity(Intent(this, GamesActivity::class.java))
            }
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh user data when returning to main menu
        viewModel.loadUserData()
    }
}