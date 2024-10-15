package ru.madj0ng.effectivemobiletest.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.madj0ng.effectivemobiletest.data.dto.AllDto

interface ApiService {
    @GET("u/0/uc")
    suspend fun getAll(
        @Query("id") id: String
    ): AllDto?
}