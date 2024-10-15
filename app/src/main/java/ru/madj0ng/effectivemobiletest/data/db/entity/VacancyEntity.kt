package ru.madj0ng.effectivemobiletest.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy_table")
data class VacancyEntity(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val address: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: String,
    val schedules: String,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    val questions: String,
)
