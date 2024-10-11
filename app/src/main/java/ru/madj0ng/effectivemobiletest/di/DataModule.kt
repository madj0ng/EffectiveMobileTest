package ru.madj0ng.effectivemobiletest.di

import android.content.Intent
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.madj0ng.effectivemobiletest.data.db.AppDatabase
import ru.madj0ng.effectivemobiletest.data.network.ApiService
import ru.madj0ng.effectivemobiletest.data.network.NetworkClient
import ru.madj0ng.effectivemobiletest.data.network.RetrofitNetworkClient
import ru.madj0ng.effectivemobiletest.util.CheckConnect

val dataModule = module {
    single {
        CheckConnect(androidContext())
    }

    single {
        synchronized(this) {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db").build()
        }
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(
            connected = get(),
            api = get()
        )
    }

    factory { Intent() }
}