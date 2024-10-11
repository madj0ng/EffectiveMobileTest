package ru.madj0ng.effectivemobiletest.data.network

import ru.madj0ng.effectivemobiletest.data.dto.NetworkResponse
import ru.madj0ng.effectivemobiletest.data.dto.OffersRequest
import ru.madj0ng.effectivemobiletest.data.dto.OffersResponse
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesRequest
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesResponse
import ru.madj0ng.effectivemobiletest.util.CheckConnect

const val ERROR_CODE = -1
const val SUCCESS_CODE = 200
const val FILE_KEY = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r"

class RetrofitNetworkClient(
    private val connected: CheckConnect,
    private val api: ApiService,
) : NetworkClient {

    override suspend fun doRequest(dto: Any): NetworkResponse {
        return if (connected.isConnected()) {
            when (dto) {
                is OffersRequest -> offersRequest(dto)
                is VacanciesRequest -> vacanciesRequest(dto)
                else -> errorResponse()
            }
        } else {
            errorResponse()
        }
    }

    private suspend fun offersRequest(offersRequest: OffersRequest): OffersResponse {
        val result = api.getAll(offersRequest.request)
        return if (result?.offers != null) {
            OffersResponse(result.offers).apply { resultCode = SUCCESS_CODE }
        } else {
            OffersResponse(listOf()).apply { resultCode = SUCCESS_CODE }
        }
    }

    private suspend fun vacanciesRequest(vacanciesRequest: VacanciesRequest): VacanciesResponse {
        val result = api.getAll(vacanciesRequest.request)
        return if (result?.vacancies != null && result.vacancies.isNotEmpty()) {
            val listMin = 0
            val listMax =
                (if (vacanciesRequest.listCount <= 0) result.vacancies.size else vacanciesRequest.listCount) - 1

            VacanciesResponse(
                result.vacancies.slice(listMin..listMax),
                result.vacancies.size
            ).apply { resultCode = SUCCESS_CODE }
        } else {
            VacanciesResponse(listOf()).apply { resultCode = SUCCESS_CODE }
        }
    }

    private fun errorResponse() = NetworkResponse().apply {
        resultCode = ERROR_CODE
    }
}