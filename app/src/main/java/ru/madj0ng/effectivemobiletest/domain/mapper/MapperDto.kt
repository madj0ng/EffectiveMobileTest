package ru.madj0ng.effectivemobiletest.domain.mapper

import ru.madj0ng.effectivemobiletest.data.dto.VacancyDto
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

class MapperDto {
    fun map(dto: VacancyDto): VacancyModel = VacancyModel(
        dto.id,
        dto.lookingNumber ?: 0,
        dto.title,
        dto.address,
        dto.company,
        dto.experience,
        dto.publishedDate,
        dto.isFavorite,
        dto.salary,
        dto.schedules,
        dto.appliedNumber ?: 0,
        dto.description ?: "",
        dto.responsibilities,
        dto.questions
    )
}