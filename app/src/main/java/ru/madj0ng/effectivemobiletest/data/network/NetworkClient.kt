package ru.madj0ng.effectivemobiletest.data.network

import ru.madj0ng.effectivemobiletest.data.dto.NetworkResponse

interface NetworkClient {
    suspend fun doRequest(dto: Any): NetworkResponse
}