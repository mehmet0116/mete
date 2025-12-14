package com.metegelistirme.models

data class Game(
    val id: Int,
    val title: String,
    val description: String,
    val iconResId: Int,
    val backgroundColor: Int,
    val gameType: GameType
)

enum class GameType {
    MATCHING,
    PUZZLE,
    MEMORY,
    QUIZ
}

