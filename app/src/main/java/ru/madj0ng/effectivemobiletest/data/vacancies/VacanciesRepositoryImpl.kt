package ru.madj0ng.effectivemobiletest.data.vacancies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesRequest
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesResponse
import ru.madj0ng.effectivemobiletest.data.mapper.MapperRequest
import ru.madj0ng.effectivemobiletest.data.network.FILE_KEY
import ru.madj0ng.effectivemobiletest.data.network.NetworkClient
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesRepository

class VacanciesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val mapper: MapperRequest
) : VacanciesRepository {
    override fun getVacancies(request: VacanciesRequestParam): Flow<Resource<List<VacanciesDto>>> = flow {
        val response = networkClient.doRequest(mapper.map(request))
        emit(
            if (response is VacanciesResponse) {
                Resource.Success(response.vacancies, response.listSize)
            } else {
                Resource.Error("")
            }
        )
    }
}