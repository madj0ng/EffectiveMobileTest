package ru.madj0ng.effectivemobiletest.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.madj0ng.effectivemobiletest.data.db.entity.VacancyEntity

@Dao
interface FavoritesDao {
    @Insert(entity = VacancyEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancyEntity: VacancyEntity)

    @Delete(entity = VacancyEntity::class)
    suspend fun deleteVacancy(vacancyEntity: VacancyEntity)

    @Query("SELECT * FROM vacancy_table")
    suspend fun getVacancies(): List<VacancyEntity>?

    @Query("SELECT * FROM vacancy_table WHERE id = :vacancyId")
    suspend fun getVacancies(vacancyId: String): VacancyEntity?
}