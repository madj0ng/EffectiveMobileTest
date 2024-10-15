package ru.madj0ng.effectivemobiletest.domain.models

sealed interface Resource<T> {
    data class Success<T>(val data: T, val size: Int = 0) : Resource<T>

    data class Error<T>(val message: String) : Resource<T>
}