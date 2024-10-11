package ru.madj0ng.effectivemobiletest.data.dto

import com.google.gson.annotations.SerializedName

data class OfferDto(
    val id: String?,
    val title: String,
    val link: String,
    @SerializedName("button")
    val button: ButtonDto?,
)
