package ru.madj0ng.effectivemobiletest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteInteractor
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.domain.offers.OffersUseCase
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingUseCase
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractor
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractorImpl
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState

class SearchViewModel(
    private val offers: OffersUseCase,
    private val vacancies: VacanciesInteractor,
    private val sharing: SharingUseCase,
    private val favorite: FavoriteInteractor
) : ViewModel() {

    private val offersData = MutableLiveData<List<OfferDto>>()
    fun getOffers(): LiveData<List<OfferDto>> = offersData
    private val vacanciesData = MutableLiveData<VacanciesUiState>(VacanciesUiState.Loading)
    fun getVacancies(): LiveData<VacanciesUiState> = vacanciesData
    private val defaultData = MutableLiveData<Boolean>()
    fun getDefault(): LiveData<Boolean> = defaultData

    private var defaultMax = 3

    init {
        setOffers()
        setVacancies(defaultMax)
    }

    fun nextPage() {
        setVacancies()
        setDefault(true)
    }

    fun backPage() {
        setVacancies(defaultMax)
        setDefault(false)
    }

    fun sharingOffer(urlString: String) {
        sharing.openLink(urlString)
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
        val current = vacanciesData.value
        if (current is VacanciesUiState.Content) {
            val movieIndex = current.list.indexOfFirst { it.id == vacancyId }
            if (movieIndex != -1) {
                vacanciesData.value = VacanciesUiState.Content(
                    current.list.toMutableList().also { it[movieIndex] = vacancy },
                    current.count
                )
            }
        }
    }

    private fun setDefault(isDefault: Boolean) {
        if (defaultData.value != isDefault) {
            defaultData.value = isDefault
        }
    }

    private fun setOffers() {
        viewModelScope.launch {
            offers().collect {
                when (it) {
                    is Resource.Error -> renderOfferError(it.message)
                    is Resource.Success -> renderOfferSuccess(it.data)
                }
            }
        }
    }

    private fun setVacancies(count: Int = 0) {
        vacanciesData.postValue(VacanciesUiState.Loading)
        viewModelScope.launch {
            vacancies(count).collect {
                when (it) {
                    is Resource.Error -> renderVacanciesError(it.message)
                    is Resource.Success -> renderVacanciesSuccess(it.data, it.size)
                }
            }
        }
    }

    private fun renderOfferSuccess(list: List<OfferDto>) {
        offersData.postValue(list)
    }

    private fun renderOfferError(message: String) {}

    private fun renderVacanciesSuccess(list: List<VacancyModel>, size: Int) {
        vacanciesData.postValue(VacanciesUiState.Content(list, size))
    }

    private fun renderVacanciesError(message: String) {}
}