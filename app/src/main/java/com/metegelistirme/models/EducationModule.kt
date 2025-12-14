package com.metegelistirme.models

data class EducationModule(
    val id: Int,
    val title: String,
    val description: String,
    val iconResId: Int,
    val backgroundColor: Int,
    val moduleType: ModuleType
)

enum class ModuleType {
    LANGUAGE,
    MATH,
    COGNITIVE,
    CREATIVITY,
    LIFE_SKILLS
}

