package com.builtinmedia.hris.features.auth.domain.repositories

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.auth.domain.entities.UserEntities


interface AuthRepositories {
    suspend fun login(email: String, password: String): Either<ApiException, UserEntities>
    suspend fun logout(): Either<ApiException, String>

    suspend fun getDataUser(): Either<ApiException, UserEntities>
}