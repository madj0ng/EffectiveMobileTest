package ru.madj0ng.effectivemobiletest.domain.sharing

interface SharingRepository {
    fun openLink(urlString: String)
}