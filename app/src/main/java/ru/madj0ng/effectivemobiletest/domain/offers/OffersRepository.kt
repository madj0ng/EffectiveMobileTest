package ru.madj0ng.effectivemobiletest.domain.offers

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.domain.models.Resource

interface OffersRepository {
    fun getOffers(): Flow<Resource<List<OfferDto>>>
}