package ru.madj0ng.effectivemobiletest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacanciesRequestParam
import ru.madj0ng.effectivemobiletest.domain.offers.OffersUseCase
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesUseCase
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState

class SearchViewModel(
    private val offers: OffersUseCase,
    private val vacancies: VacanciesUseCase
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
        setVacancies(VacanciesRequestParam(defaultMax))
    }

    fun nextPage() {
        setVacancies(VacanciesRequestParam())
        setDefault(true)
    }

    fun backPage() {
        setVacancies(VacanciesRequestParam(defaultMax))
        setDefault(false)
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

    private fun setVacancies(param: VacanciesRequestParam) {
        viewModelScope.launch {
            vacancies(param).collect {
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

    private fun renderVacanciesSuccess(list: List<VacanciesDto>, size: Int) {
        vacanciesData.postValue(VacanciesUiState.Content(list, size))
    }

    private fun renderVacanciesError(message: String) {}
}