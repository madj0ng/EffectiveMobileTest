package ru.madj0ng.effectivemobiletest.data.dto

data class VacancyResponse(
    val vacancy: VacancyDto?
) : NetworkResponse()
