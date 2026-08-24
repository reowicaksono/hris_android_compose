package com.builtinmedia.hris.features.auth.domain.usecase

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.auth.domain.repositories.AuthRepositories
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepositories
) {
    suspend operator fun invoke(): Either<ApiException, String> = repository.logout()
}