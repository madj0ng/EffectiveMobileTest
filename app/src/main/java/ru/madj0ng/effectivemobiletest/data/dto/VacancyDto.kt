package ru.madj0ng.effectivemobiletest.data.dto

import com.google.gson.annotations.SerializedName
import ru.madj0ng.effectivemobiletest.domain.models.Address
import ru.madj0ng.effectivemobiletest.domain.models.Experience
import ru.madj0ng.effectivemobiletest.domain.models.Salary

data class VacancyDto(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    @SerializedName("address")
    val address: Address,
    val company: String,
    @SerializedName("experience")
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    @SerializedName("salary")
    val salary: Salary,
    @SerializedName("schedules")
    val schedules: List<String>,
    val appliedNumber: Int?,
    val description: String?,
    val responsibilities: String,
    @SerializedName("questions")
    val questions: List<String>,
)
