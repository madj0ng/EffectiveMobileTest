package ru.madj0ng.effectivemobiletest.data.mapper

import ru.madj0ng.effectivemobiletest.data.dto.VacanciesRequest
import ru.madj0ng.effectivemobiletest.data.network.FILE_KEY
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam

class MapperRequest {
    fun map(param: VacanciesRequestParam): VacanciesRequest =
        VacanciesRequest(FILE_KEY, param.listCount)
}