package ru.madj0ng.effectivemobiletest.data.dto

data class VacanciesResponse (
    val vacancies: List<VacanciesDto>,
    val listSize: Int = 0
) : NetworkResponse()
