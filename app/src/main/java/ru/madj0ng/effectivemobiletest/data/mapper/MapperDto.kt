package ru.madj0ng.effectivemobiletest.data.mapper

import ru.madj0ng.effectivemobiletest.data.dto.VacancyDto
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.util.FormatDate

class MapperDto(
    private val formatDate: FormatDate
) {
    fun map(dto: VacancyDto): VacancyModel = VacancyModel(
        dto.id,
        dto.lookingNumber,
        dto.title,
        dto.address.town,
        dto.company,
        dto.experience.previewText,
        dto.publishedDate,
        formatDate.formatDateToDayMonth(dto.publishedDate),
        dto.isFavorite,
        dto.salary.short,
        dto.salary.full,
        dto.description,
        dto.responsibilities,
    )
}