package ru.madj0ng.effectivemobiletest.domain.vacancies

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

interface VacanciesRepository {
    fun getVacancies(count: Int): Flow<Resource<List<VacancyModel>>>
    fun getVacancies(vacancyId: String): Flow<Resource<VacancyModel>>
}