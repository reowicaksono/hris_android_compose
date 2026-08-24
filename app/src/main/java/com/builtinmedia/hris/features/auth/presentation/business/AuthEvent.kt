package com.builtinmedia.hris.features.auth.presentation.business

sealed interface AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent
    data class PasswordChanged(val password: String) : AuthEvent
    data object TogglePasswordVisibility : AuthEvent
    data class RememberMeChanged(val rememberMe: Boolean): AuthEvent
    data object Submit : AuthEvent
    data object ErrorShown : AuthEvent
}