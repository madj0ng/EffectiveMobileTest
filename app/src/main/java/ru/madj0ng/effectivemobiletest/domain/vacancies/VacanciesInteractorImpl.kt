package ru.madj0ng.effectivemobiletest.domain.vacancies

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VacanciesInteractorImpl(
    private val vacanciesRepository: VacanciesRepository
) : VacanciesInteractor {
    override suspend operator fun invoke(count: Int) =
        withContext(Dispatchers.IO) { vacanciesRepository.getVacancies(count) }

    override suspend operator fun invoke(vacancyId: String) =
        withContext(Dispatchers.IO) { vacanciesRepository.getVacancies(vacancyId) }
}