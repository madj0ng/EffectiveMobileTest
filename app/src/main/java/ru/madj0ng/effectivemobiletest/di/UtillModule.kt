package ru.madj0ng.effectivemobiletest.di

import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.data.mapper.MapperDb
import ru.madj0ng.effectivemobiletest.data.mapper.MapperDto
import ru.madj0ng.effectivemobiletest.data.mapper.MapperRequest
import ru.madj0ng.effectivemobiletest.util.FormatDate
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

val utillModule = module {
    single { FormatDate() }

    single { NumericDeclination() }

    factory { MapperRequest() }

    factory { MapperDb(get()) }

    factory { MapperDto(get()) }
}