package ru.madj0ng.effectivemobiletest.data.dto

data class VacanciesResponse (
    val vacancies: List<VacancyDto>,
    val listSize: Int = 0
) : NetworkResponse()
