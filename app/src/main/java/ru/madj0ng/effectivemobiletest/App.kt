package ru.madj0ng.effectivemobiletest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.madj0ng.effectivemobiletest.di.dataModule
import ru.madj0ng.effectivemobiletest.di.repositoryModule
import ru.madj0ng.effectivemobiletest.di.useCaseModule
import ru.madj0ng.effectivemobiletest.di.utillModule
import ru.madj0ng.effectivemobiletest.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, useCaseModule, viewModelModule, utillModule)
        }
    }
}