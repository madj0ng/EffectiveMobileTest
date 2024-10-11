package ru.madj0ng.effectivemobiletest.di

import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.data.offers.OffersRepositoryImpl
import ru.madj0ng.effectivemobiletest.data.vacancies.VacanciesRepositoryImpl
import ru.madj0ng.effectivemobiletest.domain.offers.OffersRepository
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesRepository

val repositoryModule = module {
    factory<VacanciesRepository> {
        VacanciesRepositoryImpl(
            networkClient = get(),
            mapper = get())
    }

    factory<OffersRepository> {
        OffersRepositoryImpl(networkClient = get())
    }
}