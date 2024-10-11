package ru.madj0ng.effectivemobiletest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingUseCase
import ru.madj0ng.effectivemobiletest.presentation.SearchViewModel

val viewModelModule = module {
    viewModel {
        SearchViewModel(
            offers = get(),
            vacancies = get(),
            sharing = get()
        )
    }
}