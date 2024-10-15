package ru.madj0ng.effectivemobiletest.di

import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteInteractor
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteInteractorImpl
import ru.madj0ng.effectivemobiletest.domain.offers.OffersUseCase
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingUseCase
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractor
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractorImpl

val useCaseModule = module {
    factory {
        OffersUseCase(offresRepository = get())
    }

    factory {
        SharingUseCase(repository = get())
    }

    factory<VacanciesInteractor> {
        VacanciesInteractorImpl(vacanciesRepository = get())
    }

    factory<FavoriteInteractor> {
        FavoriteInteractorImpl(favoriteRepository = get())
    }
}