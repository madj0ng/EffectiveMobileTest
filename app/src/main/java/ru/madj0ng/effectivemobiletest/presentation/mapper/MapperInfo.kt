package ru.madj0ng.effectivemobiletest.presentation.mapper

import android.content.Context
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.presentation.models.VacancyInfo
import ru.madj0ng.effectivemobiletest.util.FormatDate

class MapperInfo(
    private val context: Context,
    private val formatDate: FormatDate
) {
    private val empty = ""
    private val separator = ", "
    fun map(vacancy: VacancyModel): VacancyInfo {
        return VacancyInfo(
            vacancy.id,
//            context.getString(
//                R.string.count_viewers_now,
//                vacancy.lookingNumber, NumericDeclination().masculine(vacancy.lookingNumber)
//            ) ?: "",
            vacancy.lookingNumber,
            vacancy.title,
            vacancy.address.town,
            listOf(
                vacancy.address.town,
                vacancy.address.street,
                vacancy.address.house
            ).joinToString(separator = separator),
            vacancy.company,
            vacancy.experience.previewText,
            formatDate.formatDateToDayMonth(vacancy.publishedDate),
            vacancy.isFavorite,
            vacancy.salary.full,
            vacancy.schedules.joinToString(separator = separator),
            vacancy.appliedNumber,
            vacancy.description ?: empty,
            vacancy.responsibilities,
            vacancy.questions,
        )
    }
}