package ru.madj0ng.effectivemobiletest.presentation.models

data class VacancyInfo(
    val id: String,
    val lookingNumber: Int,
    val title: String,
    val town: String,
    val addressFull: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String,
    val schedules: String,
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: List<String>,
)