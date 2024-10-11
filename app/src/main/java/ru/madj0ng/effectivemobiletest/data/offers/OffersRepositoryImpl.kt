package ru.madj0ng.effectivemobiletest.data.offers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.data.dto.OffersRequest
import ru.madj0ng.effectivemobiletest.data.dto.OffersResponse
import ru.madj0ng.effectivemobiletest.data.network.FILE_KEY
import ru.madj0ng.effectivemobiletest.data.network.NetworkClient
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam
import ru.madj0ng.effectivemobiletest.domain.offers.OffersRepository

class OffersRepositoryImpl(
    private val networkClient: NetworkClient
) : OffersRepository {
    override fun getOffers(): Flow<Resource<List<OfferDto>>> = flow {
        val response = networkClient.doRequest(OffersRequest(FILE_KEY))
        emit(
            if (response is OffersResponse) {
                Resource.Success(response.offers)
            } else {
                Resource.Error("")
            }
        )
    }
}