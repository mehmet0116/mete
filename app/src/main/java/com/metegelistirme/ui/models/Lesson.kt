package com.metegelistirme.ui.models

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val colorRes: Int,
    val iconRes: Int? = null
)
