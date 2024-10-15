package ru.madj0ng.effectivemobiletest.presentation.models

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
    val login: String
    //... other data fields that may be accessible to the UI
)