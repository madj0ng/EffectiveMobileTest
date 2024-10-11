package ru.madj0ng.effectivemobiletest.data.mapper

import ru.madj0ng.effectivemobiletest.data.dto.VacanciesRequest
import ru.madj0ng.effectivemobiletest.data.dto.VacancyRequest
import ru.madj0ng.effectivemobiletest.data.network.FILE_KEY

class MapperRequest {
    fun map(count: Int): VacanciesRequest =
        VacanciesRequest(FILE_KEY, count)

    fun map(vacancyId: String): VacancyRequest =
        VacancyRequest(FILE_KEY, vacancyId)
}