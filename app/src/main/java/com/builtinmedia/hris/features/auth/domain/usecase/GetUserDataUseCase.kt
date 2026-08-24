package com.builtinmedia.hris.features.auth.domain.usecase

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.auth.domain.entities.UserEntities
import com.builtinmedia.hris.features.auth.domain.repositories.AuthRepositories
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val authRepositories: AuthRepositories
) {
    suspend operator fun invoke(): Either<ApiException, UserEntities> =
        authRepositories.getDataUser()
}