package ru.madj0ng.effectivemobiletest.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperDb
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperDto
import ru.madj0ng.effectivemobiletest.domain.mapper.MapperRequest
import ru.madj0ng.effectivemobiletest.presentation.mapper.MapperInfo
import ru.madj0ng.effectivemobiletest.util.FormatDate
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

val utillModule = module {
    single { FormatDate() }

    single { NumericDeclination() }

    factory { MapperRequest() }

    single { MapperDb(get()) }

    factory { MapperDto() }

    factory {
        MapperInfo(
            context = androidContext(),
            formatDate = get()
        )
    }
}