package ru.madj0ng.effectivemobiletest.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.domain.offers.OffersUseCase
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingUseCase
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesUseCase

val useCaseModule = module {
    factory {
        OffersUseCase(offresRepository = get())
    }

    factory {
        VacanciesUseCase(vacanciesRepository = get())
    }

    factory {
        SharingUseCase(
            repository = get()
        )
    }
}