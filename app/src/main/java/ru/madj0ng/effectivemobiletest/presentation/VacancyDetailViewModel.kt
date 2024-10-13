package ru.madj0ng.effectivemobiletest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.madj0ng.effectivemobiletest.domain.models.Resource
import ru.madj0ng.effectivemobiletest.domain.vacancies.VacanciesInteractor
import ru.madj0ng.effectivemobiletest.presentation.mapper.MapperInfo
import ru.madj0ng.effectivemobiletest.presentation.models.VacancyInfo

class VacancyDetailViewModel(
    private val vacancies: VacanciesInteractor,
    private val mapperInfo: MapperInfo,
) : ViewModel() {
    private val vacancyData = MutableLiveData<VacancyInfo>()
    fun getVacancy(): LiveData<VacancyInfo> = vacancyData

    private val questionData = MutableLiveData<List<String>>()
    fun getQuestion(): LiveData<List<String>> = questionData

    fun loadData(vacancyId: String) {
        viewModelScope.launch {
            vacancies(vacancyId).collect {
                when (it) {
                    is Resource.Error -> {}
                    is Resource.Success -> renderVacancySuccess(mapperInfo.map(it.data))
                }
            }
        }
    }

    private fun renderVacancySuccess(vacancy: VacancyInfo) {
        vacancyData.postValue(vacancy)
        questionData.postValue(vacancy.questions)
    }
}