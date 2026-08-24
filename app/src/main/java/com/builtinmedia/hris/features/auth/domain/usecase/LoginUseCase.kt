package com.builtinmedia.hris.features.auth.domain.usecase

import arrow.core.Either
import arrow.core.left
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.auth.domain.entities.UserEntities
import com.builtinmedia.hris.features.auth.domain.repositories.AuthRepositories
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepositories: AuthRepositories
) {
    suspend operator fun invoke(email: String, password: String): Either<ApiException, UserEntities>{
        if(email.isBlank() || password.isBlank()){
            return ApiException.BadRequest("Email atau password tidak boleh kosong").left()
        }
        if(!email.contains('@')){
            return ApiException.BadRequest("Email tidak valid").left()
        }
        return authRepositories.login(email.trim(), password)
    }
}