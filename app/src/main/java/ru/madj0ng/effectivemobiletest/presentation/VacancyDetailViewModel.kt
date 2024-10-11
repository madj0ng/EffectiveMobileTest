package ru.madj0ng.effectivemobiletest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractor

class VacancyDetailViewModel(
    private val vacancies: VacanciesInteractor,
) : ViewModel() {
    private val vacancyData = MutableLiveData<VacancyModel>()
    fun getVacancy(): LiveData<VacancyModel> = vacancyData

    fun loadData(vacancyId: String) {
        viewModelScope.launch {
            vacancies(vacancyId).collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Success -> renderVacancySuccess(it.data)
                }
            }
        }
    }

    private fun renderVacancySuccess(vacancy: VacancyModel) {
        vacancyData.postValue(vacancy)
    }
}