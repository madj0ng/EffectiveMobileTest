package ru.madj0ng.effectivemobiletest.data.favorite

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.madj0ng.effectivemobiletest.data.db.AppDatabase
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperDb
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteRepository
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

class FavoriteRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val mapperDb: MapperDb,
) : FavoriteRepository {
    override suspend fun insertFavorite(favorite: VacancyModel) {
        val entity = mapperDb.map(favorite.copy(isFavorite = true))
        appDatabase.favoritesDao().insertVacancy(entity)
    }

    override suspend fun deleteFavorite(favorite: VacancyModel) {
        val entity = mapperDb.map(favorite.copy(isFavorite = true))
        appDatabase.favoritesDao().deleteVacancy(entity)
    }

    override fun getFavorites(favorite: VacancyModel): Flow<Resource<VacancyModel>> = flow {
        val entity = appDatabase.favoritesDao().getVacancies(favorite.id)
        emit(
            if (entity != null) {
                Resource.Success(mapperDb.map(entity))
            } else {
                Resource.Error("")
            }
        )
    }

    override fun getFavorites(): Flow<Resource<List<VacancyModel>>> = flow {
        val list = appDatabase.favoritesDao().getVacancies()
        emit(
            if (list != null) {
                Resource.Success(list.map { mapperDb.map(it) }, list.size)
            } else {
                Resource.Error("")
            }
        )
    }
}