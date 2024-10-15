package ru.madj0ng.effectivemobiletest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteInteractor
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState

class FavoriteViewModel(
    private val favorite: FavoriteInteractor,
) : ViewModel() {

    private val favoritesData = MutableLiveData<VacanciesUiState>(VacanciesUiState.Loading)
    fun getFavorites(): LiveData<VacanciesUiState> = favoritesData

    private val favoriteCountData = MutableLiveData<Int>()
    fun getFavoriteCount(): LiveData<Int> = favoriteCountData

    fun loadData() {
        viewModelScope.launch {
            favorite.getFavorites().collect {
                when (it) {
                    is Resource.Error -> renderFavoritesError(it.message)
                    is Resource.Success -> renderFavoritesSuccess(it.data, it.size)
                }
            }
        }
    }

    fun toggleFavorite(vacancy: VacancyModel) {
        viewModelScope.launch {
            if (vacancy.isFavorite) {
                favorite.deleteFavorite(vacancy)
            } else {
                favorite.insertFavorite(vacancy)
            }
        }
        updateContent(vacancy.id, vacancy.copy(isFavorite = !vacancy.isFavorite))
    }

    private fun updateContent(vacancyId: String, vacancy: VacancyModel) {
        val current = favoritesData.value
        if (current is VacanciesUiState.Content) {
            val movieIndex = current.list.indexOfFirst { it.id == vacancyId }
            if (movieIndex != -1) {
                favoritesData.value = VacanciesUiState.Content(
                    current.list.toMutableList().also { it.removeAt(movieIndex) },
                    current.count
                )
            }
        }
    }

    private fun renderFavoritesSuccess(list: List<VacancyModel>, count: Int) {
        favoriteCountData.postValue(count)
        favoritesData.postValue(VacanciesUiState.Content(list, count))
    }

    private fun renderFavoritesError(message: String) {}
}