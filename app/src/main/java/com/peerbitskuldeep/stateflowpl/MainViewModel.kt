package com.peerbitskuldeep.stateflowpl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    //StateFlow needs to have an initial-value, LiveData doesn't, it's an optional
    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)

    //for the access in mainActivity which is immutableStateFlow
    val loginUiState : StateFlow<LoginUiState> = _loginUiState

    fun loginCredential(userName: String, password: String) = viewModelScope.launch {

        _loginUiState.value = LoginUiState.Loading

        delay(2000L)

        if (userName == "peerbits" && password == "123")
        {
            _loginUiState.value = LoginUiState.Success
        }
        else
        {
            _loginUiState.value = LoginUiState.Error("Wrong Credential!")
        }

    }

    sealed class LoginUiState
    {
        object Success: LoginUiState()
        data class Error(val msg: String): LoginUiState()
        object Loading: LoginUiState()
        object Empty: LoginUiState()
    }

}