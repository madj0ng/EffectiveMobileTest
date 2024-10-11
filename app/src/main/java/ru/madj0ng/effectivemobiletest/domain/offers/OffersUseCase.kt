package ru.madj0ng.effectivemobiletest.domain.offers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam

class OffersUseCase(private val offresRepository: OffersRepository) {
    suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            offresRepository.getOffers()
        }
}