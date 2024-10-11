package ru.madj0ng.effectivemobiletest.data.dto

import com.google.gson.annotations.SerializedName

data class VacanciesDto(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    @SerializedName("address")
    val address: AdressDto?,
    val company: String,
    @SerializedName("experience")
    val experience: ExperienceDto,
    val publishedDate: String,
    val isFavorite: Boolean,
    @SerializedName("salary")
    val salary: SalaryDto,
    @SerializedName("schedules")
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    @SerializedName("questions")
    val questions: List<String>,
)
