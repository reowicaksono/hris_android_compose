package com.builtinmedia.hris.features.auth.presentation.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.builtinmedia.hris.features.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AuthUiEvent {
    data object NavigateHome : AuthUiEvent
    data class ShowSnackbar(val message: String) : AuthUiEvent
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    private val _uiEvent = Channel<AuthUiEvent>(
        Channel.BUFFERED
    )
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged ->
                _state.update { it.copy(email = event.email, errorMessage = null) }

            is AuthEvent.PasswordChanged ->
                _state.update { it.copy(password = event.password, errorMessage = null) }

            is AuthEvent.TogglePasswordVisibility ->
                _state.update { it.copy(isVisibility = !it.isVisibility) }

            is AuthEvent.RememberMeChanged ->
                _state.update { it.copy(rememberMe = event.rememberMe) }

            AuthEvent.ErrorShown ->
                _state.update { it.copy(errorMessage = null) }

            AuthEvent.Submit -> login()
        }

    }

    private fun login() {
        val current = _state.value
        if (!current.isSubmitted) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = loginUseCase(current.email, current.password)) {
                is Either.Right -> {
                    _state.update { it.copy(isLoading = false) }
                    _uiEvent.send(AuthUiEvent.NavigateHome)
                }

                is Either.Left -> {
                    val message = result.value.message ?: "Terjadi kesalahan"

                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _uiEvent.send(AuthUiEvent.ShowSnackbar(message))

                }
            }

        }
    }
}