package com.metegelistirme.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.metegelistirme.R
import com.metegelistirme.activities.games.MatchingGameActivity
import com.metegelistirme.activities.games.SimplePuzzleActivity
import com.metegelistirme.activities.games.MemoryGameActivity
import com.metegelistirme.activities.games.QuizGameActivity
import com.metegelistirme.adapters.GamesAdapter
import com.metegelistirme.databinding.ActivityGamesBinding
import com.metegelistirme.models.Game
import com.metegelistirme.models.GameType

class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding
    private lateinit var gamesAdapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar setup
        binding.toolbar.title = getString(R.string.games_title)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        setupRecyclerView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupRecyclerView() {
        val games = getGames()
        gamesAdapter = GamesAdapter(games) { game ->
            navigateToGame(game)
        }

        binding.gamesRecyclerView.apply {
            layoutManager = GridLayoutManager(this@GamesActivity, 2)
            adapter = gamesAdapter
        }
    }

    private fun getGames(): List<Game> {
        return listOf(
            Game(
                id = 1,
                title = getString(R.string.matching_game),
                description = "Resimleri eşleştir",
                iconResId = R.drawable.ic_games,
                backgroundColor = R.color.primary_color,
                gameType = GameType.MATCHING
            ),
            Game(
                id = 2,
                title = getString(R.string.puzzle_game),
                description = "Parçaları yerleştir",
                iconResId = R.drawable.ic_games,
                backgroundColor = R.color.secondary_color,
                gameType = GameType.PUZZLE
            ),
            Game(
                id = 3,
                title = getString(R.string.memory_game),
                description = "Hafızanı test et",
                iconResId = R.drawable.ic_games,
                backgroundColor = R.color.accent_color,
                gameType = GameType.MEMORY
            ),
            Game(
                id = 4,
                title = getString(R.string.quiz_game),
                description = "Soruları cevapla",
                iconResId = R.drawable.ic_games,
                backgroundColor = R.color.success_green,
                gameType = GameType.QUIZ
            )
        )
    }

    private fun navigateToGame(game: Game) {
        val intent = when (game.gameType) {
            GameType.MATCHING -> Intent(this, MatchingGameActivity::class.java)
            GameType.PUZZLE -> Intent(this, SimplePuzzleActivity::class.java)
            GameType.MEMORY -> Intent(this, MemoryGameActivity::class.java)
            GameType.QUIZ -> Intent(this, QuizGameActivity::class.java)
        }
        startActivity(intent)
    }
}
