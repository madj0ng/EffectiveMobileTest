package ru.madj0ng.effectivemobiletest.data.dto

import com.google.gson.annotations.SerializedName

data class AllDto(
    @SerializedName("offers")
    val offers: List<OfferDto>?,
    @SerializedName("vacancies")
    val vacancies: List<VacanciesDto>?
)
