package ru.madj0ng.effectivemobiletest.domain.vacancies

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

interface VacanciesInteractor {
    suspend operator fun invoke(count: Int): Flow<Resource<List<VacancyModel>>>
    suspend operator fun invoke(vacancyId: String): Flow<Resource<VacancyModel>>
}