package com.builtinmedia.hris.features.presence.presentation.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.builtinmedia.hris.features.auth.domain.usecase.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


//temporary create state and event with 1 file bcs doesnt overengineering
data class PresenceState(
    val isLoggingOut: Boolean = false,
)
sealed class PresenceUiEvent {
    data object NavigateToLogin: PresenceUiEvent()
    data class ShowSnackbar(val message: String): PresenceUiEvent()
}

@HiltViewModel
class PresenceViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PresenceState())
    val state: StateFlow<PresenceState> = _state.asStateFlow()

    private val _uiEvent = Channel<PresenceUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onLogoutClick(){
        if (state.value.isLoggingOut) return

        viewModelScope.launch {
            _state.update { it.copy(isLoggingOut = true) }

            when (val result = logoutUseCase()){
                is Either.Right -> {
                    _state.update { it.copy(isLoggingOut = false) }
                    _uiEvent.send(PresenceUiEvent.NavigateToLogin)
                }
                is Either.Left -> {
                    _state.update { it.copy(isLoggingOut = false) }
                    _uiEvent.send(PresenceUiEvent.NavigateToLogin)
                }
            }
        }
    }
}