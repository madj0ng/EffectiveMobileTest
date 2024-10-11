package ru.madj0ng.effectivemobiletest.presentation.models

import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto

sealed interface VacanciesUiState {
    data object Loading : VacanciesUiState

    data class Content(
        val list: List<VacanciesDto>,
        val count: Int,
    ) : VacanciesUiState
}