package ru.madj0ng.effectivemobiletest.domain.models

data class VacancyModel(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val town: String,
    val company: String,
    val previewText: String,
    val publishedDate: String,
    val formatedPublishedDate: String,
    val isFavorite: Boolean,
    val salaryShort: String?,
    val salaryFull: String,
    val description: String?,
    val responsibilities: String,
)