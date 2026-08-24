package com.builtinmedia.hris.features.auth.presentation.business

data class AuthState(
    val email: String = "",
    val password: String = "",
    val isVisibility: Boolean = false,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
){
    val isSubmitted: Boolean
        get() = email.isNotBlank() && password.isNotBlank() && !isLoading
}
