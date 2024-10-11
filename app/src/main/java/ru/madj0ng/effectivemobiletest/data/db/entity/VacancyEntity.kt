package ru.madj0ng.effectivemobiletest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val town: String,
    val company: String,
    val previewText: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salaryShort: String?,
    val salaryFull: String,
    val description: String?,
    val responsibilities: String
)
