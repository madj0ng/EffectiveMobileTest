package ru.madj0ng.effectivemobiletest.domain.sharing

class SharingUseCase(private val repository: SharingRepository) {
    fun openLink(urlString: String) {
        repository.openLink(urlString)
    }
}