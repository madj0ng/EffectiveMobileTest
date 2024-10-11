package ru.madj0ng.effectivemobiletest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.madj0ng.effectivemobiletest.data.db.dao.FavoritesDao
import ru.madj0ng.effectivemobiletest.data.db.entity.VacancyEntity

@Database(
    version = 1,
    entities = [VacancyEntity::class]
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}