package ru.madj0ng.effectivemobiletest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.presentation.FavoriteViewModel
import ru.madj0ng.effectivemobiletest.presentation.SearchViewModel
import ru.madj0ng.effectivemobiletest.presentation.VacancyDetailViewModel

val viewModelModule = module {
    viewModel {
        SearchViewModel(
            offers = get(),
            vacancies = get(),
            sharing = get(),
            favorite = get(),
        )
    }
    viewModel {
        FavoriteViewModel(
            favorite = get()
        )
    }
    viewModel {
        VacancyDetailViewModel(
            vacancies = get(),
            mapperInfo = get()
        )
    }
}