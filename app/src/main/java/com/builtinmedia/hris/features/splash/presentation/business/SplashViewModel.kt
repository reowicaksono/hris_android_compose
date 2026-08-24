package com.builtinmedia.hris.features.splash.presentation.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.builtinmedia.hris.core.tokens.TokenProvider
import com.builtinmedia.hris.features.auth.domain.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*TEMPORARY CLASS SPLASH WITH SPLASH EVENT*/
sealed interface SplashUiEvent{
    data object NavigateToHome: SplashUiEvent
    data object NavigateToLogin: SplashUiEvent
}


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val getUserDataUseCase: GetUserDataUseCase
): ViewModel(){
    private val _uiEvent = Channel<SplashUiEvent>(Channel.BUFFERED)
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        checkSession()
    }

    private fun checkSession() {
        viewModelScope.launch {
            val token = tokenProvider.getTokenorNull()
            if(token.isNullOrEmpty()) {
                _uiEvent.send(SplashUiEvent.NavigateToLogin)
                return@launch
            }

            when (getUserDataUseCase()){
                is Either.Right -> _uiEvent.send(SplashUiEvent.NavigateToHome)

                is Either.Left -> {
                    tokenProvider.clearTokens()
                    _uiEvent.send(SplashUiEvent.NavigateToLogin)
                }
            }
        }
    }
}