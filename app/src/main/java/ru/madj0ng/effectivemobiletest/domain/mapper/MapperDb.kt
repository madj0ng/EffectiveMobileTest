package ru.madj0ng.effectivemobiletest.domain.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.madj0ng.effectivemobiletest.data.db.entity.VacancyEntity
import ru.madj0ng.effectivemobiletest.domain.models.Address
import ru.madj0ng.effectivemobiletest.domain.models.Experience
import ru.madj0ng.effectivemobiletest.domain.models.Salary
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.util.FormatDate

class MapperDb(
    private val gson: Gson
) {
    private val listType = object : TypeToken<List<String>>() {}.type

    fun map(item: VacancyEntity): VacancyModel = VacancyModel(
        item.id,
        item.lookingNumber ?: 0,
        item.title,
        gson.fromJson(item.address, Address::class.java),
        item.company,
        gson.fromJson(item.experience, Experience::class.java),
        item.publishedDate,
        item.isFavorite,
        gson.fromJson(item.salary, Salary::class.java),
        gson.fromJson(item.schedules, listType),
        item.appliedNumber ?: 0,
        item.description ?: "",
        item.responsibilities,
        gson.fromJson(item.questions, listType),
    )

    fun map(item: VacancyModel): VacancyEntity = VacancyEntity(
        item.id,
        item.lookingNumber,
        item.title,
        gson.toJson(item.address),
        item.company,
        gson.toJson(item.experience),
        item.publishedDate,
        item.isFavorite,
        gson.toJson(item.salary),
        gson.toJson(item.schedules),
        item.appliedNumber,
        item.description,
        item.responsibilities,
        gson.toJson(item.questions),
    )
}