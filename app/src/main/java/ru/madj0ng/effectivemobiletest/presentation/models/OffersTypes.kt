package ru.madj0ng.effectivemobiletest.presentation.models

import androidx.annotation.DrawableRes
import ru.madj0ng.effectivemobiletest.R

enum class OffersTypes(
    val id: String,
    @DrawableRes val icon: Int
) {
    NEAR_VACANCIES("near_vacancies", R.drawable.ic_profile_default),
    LEVEL_UP_RESUME("level_up_resume", R.drawable.ic_star_default),
    TEMPORARY_JOB("temporary_job", R.drawable.ic_note_default),
}