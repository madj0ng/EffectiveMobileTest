package ru.madj0ng.effectivemobiletest.domain.favorite

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

interface FavoriteRepository {
    suspend fun insertFavorite(favorite: VacancyModel)
    suspend fun deleteFavorite(favorite: VacancyModel)
    fun getFavorites(favorite: VacancyModel): Flow<Resource<VacancyModel>>
    fun getFavorites(): Flow<Resource<List<VacancyModel>>>
}