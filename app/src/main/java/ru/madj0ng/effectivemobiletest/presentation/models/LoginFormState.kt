package ru.madj0ng.effectivemobiletest.presentation.models

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val usernameError: Int? = null,
    val codeError: Int? = null,
    val isDataValid: Boolean = false
)