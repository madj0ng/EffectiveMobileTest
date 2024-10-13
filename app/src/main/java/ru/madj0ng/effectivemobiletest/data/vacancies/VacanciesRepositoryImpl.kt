package ru.madj0ng.effectivemobiletest.data.vacancies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.madj0ng.effectivemobiletest.data.db.AppDatabase
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesResponse
import ru.madj0ng.effectivemobiletest.data.dto.VacancyResponse
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperDto
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperRequest
import ru.madj0ng.effectivemobiletest.data.network.NetworkClient
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesRepository

class VacanciesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val appDatabase: AppDatabase,
    private val mapper: MapperRequest,
    private val mapperDto: MapperDto,
) : VacanciesRepository {
    override fun getVacancies(count: Int): Flow<Resource<List<VacancyModel>>> =
        flow {
            val response = networkClient.doRequest(mapper.map(count))
            val list = appDatabase.favoritesDao().getVacancies()
            emit(
                if (response is VacanciesResponse) {
                    Resource.Success(
                        response.vacancies.map { dto ->
                            mapperDto.map(dto.copy(isFavorite = (list?.find { entity ->
                                dto.id == entity.id
                            } != null)))
                        },
                        response.listSize
                    )
                } else {
                    Resource.Error("")
                }
            )
        }

    override fun getVacancies(vacancyId: String): Flow<Resource<VacancyModel>> = flow {
        val response = networkClient.doRequest(mapper.map(vacancyId))
        val list = appDatabase.favoritesDao().getVacancies()
        emit(
            if (response is VacancyResponse && response.vacancy != null) {
                Resource.Success(mapperDto.map(
                    response.vacancy.copy(
                        isFavorite = list?.find { it.id == response.vacancy.id } != null
                    )
                ))
            } else {
                Resource.Error("")
            }
        )
    }
}