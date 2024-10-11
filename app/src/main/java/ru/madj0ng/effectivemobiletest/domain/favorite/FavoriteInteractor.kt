package ru.madj0ng.effectivemobiletest.domain.favorite

import kotlinx.coroutines.flow.Flow
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

interface FavoriteInteractor {
    suspend fun insertFavorite(favorite: VacancyModel)
    suspend fun deleteFavorite(favorite: VacancyModel)
    suspend fun getFavorites(favorite: VacancyModel): Flow<Resource<VacancyModel>>
    suspend fun getFavorites(): Flow<Resource<List<VacancyModel>>>
}