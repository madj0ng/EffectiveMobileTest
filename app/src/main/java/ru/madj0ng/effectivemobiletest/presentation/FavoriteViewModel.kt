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
    private val favoriteInteractor: FavoriteInteractor,
) : ViewModel() {
    private val favoritesData = MutableLiveData<VacanciesUiState>(VacanciesUiState.Loading)
    fun getFavorites(): LiveData<VacanciesUiState> = favoritesData

    fun loadData() {
        viewModelScope.launch {
            favoriteInteractor.getFavorites().collect {
                when (it) {
                    is Resource.Error -> renderFavoritesError(it.message)
                    is Resource.Success -> renderFavoritesSuccess(it.data, it.size)
                }
            }
        }
    }

    private fun renderFavoritesSuccess(list: List<VacancyModel>, count: Int) {
        favoritesData.postValue(VacanciesUiState.Content(list, count))
    }

    private fun renderFavoritesError(message: String) {}
}