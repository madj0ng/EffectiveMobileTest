package ru.madj0ng.effectivemobiletest.presentation.models

import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel

sealed interface VacanciesUiState {
    data object Loading : VacanciesUiState

    data class Content(
        val list: List<VacancyModel>,
        val count: Int
    ) : VacanciesUiState
}