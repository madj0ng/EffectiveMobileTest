package ru.madj0ng.effectivemobiletest.di

import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.domain.offers.OffersUseCase
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesUseCase

val useCaseModule = module {
    factory {
        OffersUseCase(offresRepository = get())
    }
    factory {
        VacanciesUseCase(vacanciesRepository = get())
    }

}