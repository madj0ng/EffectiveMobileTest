package ru.madj0ng.effectivemobiletest.presentation.auth

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.data.auth.LoginRepository
import ru.madj0ng.effectivemobiletest.presentation.models.LoggedInUserView
import ru.madj0ng.effectivemobiletest.presentation.models.LoginFormState
import ru.madj0ng.effectivemobiletest.presentation.models.LoginResult
import ru.madj0ng.effectivemobiletest.util.SingleLiveEvent

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _codeForm = MutableLiveData<LoginFormState>()
    val codeFormState: LiveData<LoginFormState> = _codeForm

    private val _loginResult = SingleLiveEvent<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(login = username))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun codeDataChanged(num1: String?, num2: String?, num3: String?, num4: String?) {
        if (!isCodeValid(num1, num2, num3, num4)) {
            _codeForm.value = LoginFormState(codeError = R.string.invalid_username)
        } else {
            _codeForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isCodeValid(num1: String?, num2: String?, num3: String?, num4: String?): Boolean {
        return (!num1.isNullOrBlank() && !num2.isNullOrBlank() && !num3.isNullOrBlank() && !num4.isNullOrBlank())
    }
}