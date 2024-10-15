package ru.madj0ng.effectivemobiletest.domain.favorite

interface CurrenFavoriteCount {
    fun changeToggleFavorite(isFavorite: Boolean)
    fun setFavoriteCount(count: Int)
}