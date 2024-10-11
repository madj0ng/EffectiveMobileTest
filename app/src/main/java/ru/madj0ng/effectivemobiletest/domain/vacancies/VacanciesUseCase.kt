package ru.madj0ng.effectivemobiletest.domain.vacancies

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam

class VacanciesUseCase(private val vacanciesRepository: VacanciesRepository) {
    suspend operator fun invoke(request: VacanciesRequestParam) =
        withContext(Dispatchers.IO) {
            vacanciesRepository.getVacancies(request)
        }
}