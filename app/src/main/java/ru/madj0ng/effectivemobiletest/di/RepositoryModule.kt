package ru.madj0ng.effectivemobiletest.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.data.auth.LoginRepository
import ru.madj0ng.effectivemobiletest.data.favorite.FavoriteRepositoryImpl
import ru.madj0ng.effectivemobiletest.data.offers.OffersRepositoryImpl
import ru.madj0ng.effectivemobiletest.data.vacancies.VacanciesRepositoryImpl
import ru.madj0ng.effectivemobiletest.data.sharing.SharingRepositoryImpl
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteRepository
import ru.madj0ng.effectivemobiletest.domain.offers.OffersRepository
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesRepository
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingRepository

val repositoryModule = module {
    factory<VacanciesRepository> {
        VacanciesRepositoryImpl(
            networkClient = get(),
            appDatabase = get(),
            mapper = get(),
            mapperDto = get()
        )
    }

    factory<OffersRepository> {
        OffersRepositoryImpl(networkClient = get())
    }

    factory<SharingRepository> {
        SharingRepositoryImpl(
            context = androidContext(),
            intent = get()
        )
    }

    factory<FavoriteRepository> {
        FavoriteRepositoryImpl(
            appDatabase = get(),
            mapperDb = get()
        )
    }

    factory { LoginRepository(get()) }
}