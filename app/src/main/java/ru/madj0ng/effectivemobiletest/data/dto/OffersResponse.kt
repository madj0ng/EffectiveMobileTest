package ru.madj0ng.effectivemobiletest.data.dto

data class OffersResponse(
    val offers: List<OfferDto>
) : NetworkResponse()
