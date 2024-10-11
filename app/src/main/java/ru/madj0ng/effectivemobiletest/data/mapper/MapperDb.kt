package ru.madj0ng.effectivemobiletest.data.mapper

import ru.madj0ng.effectivemobiletest.data.db.entity.VacancyEntity
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.util.FormatDate

class MapperDb(
    private val formatDate: FormatDate
) {
    fun map(item: VacancyEntity): VacancyModel = VacancyModel(
        item.id,
        item.lookingNumber,
        item.title,
        item.town,
        item.company,
        item.previewText,
        item.publishedDate,
        formatDate.formatDateToDayMonth(item.publishedDate),
        item.isFavorite,
        item.salaryShort,
        item.salaryFull,
        item.description,
        item.responsibilities,
    )

    fun map(item: VacancyModel): VacancyEntity = VacancyEntity(
        item.id,
        item.lookingNumber,
        item.title,
        item.town,
        item.company,
        item.previewText,
        item.publishedDate,
        item.isFavorite,
        item.salaryShort,
        item.salaryFull,
        item.description,
        item.responsibilities,
    )
}