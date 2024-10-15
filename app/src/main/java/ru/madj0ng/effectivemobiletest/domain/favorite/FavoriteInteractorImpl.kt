package ru.madj0ng.effectivemobiletest.domain.favorite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

class FavoriteInteractorImpl(
    private val favoriteRepository: FavoriteRepository
) : FavoriteInteractor {
    override suspend fun insertFavorite(favorite: VacancyModel) =
        withContext(Dispatchers.IO) {
            favoriteRepository.insertFavorite(favorite)
        }

    override suspend fun deleteFavorite(favorite: VacancyModel) =
        withContext(Dispatchers.IO) {
            favoriteRepository.deleteFavorite(favorite)
        }

    override suspend fun getFavorites(favorite: VacancyModel): Flow<Resource<VacancyModel>> =
        withContext(Dispatchers.IO) {
            favoriteRepository.getFavorites(favorite)
        }

    override suspend fun getFavorites(): Flow<Resource<List<VacancyModel>>> =
        withContext(Dispatchers.IO) {
            favoriteRepository.getFavorites()
        }
}