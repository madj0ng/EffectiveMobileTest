package ru.madj0ng.effectivemobiletest.domain.vacancies

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam

interface VacanciesRepository {
    fun getVacancies(request: VacanciesRequestParam): Flow<Resource<List<VacanciesDto>>>
}