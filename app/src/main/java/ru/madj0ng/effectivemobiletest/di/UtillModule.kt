package ru.madj0ng.effectivemobiletest.di

import org.koin.dsl.module
import ru.madj0ng.effectivemobiletest.data.mapper.MapperRequest

val utillModule = module {
    factory { MapperRequest() }
}