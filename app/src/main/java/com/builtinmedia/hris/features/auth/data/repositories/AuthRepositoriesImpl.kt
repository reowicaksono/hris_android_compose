package com.builtinmedia.hris.features.auth.data.repositories

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.core.network.mapping.safeApiCall
import com.builtinmedia.hris.core.network.mapping.safeApiCallNoData
import com.builtinmedia.hris.core.tokens.TokenProvider
import com.builtinmedia.hris.features.auth.data.datasource.AuthRemoteDataSource
import com.builtinmedia.hris.features.auth.data.model.LoginRequestModel
import com.builtinmedia.hris.features.auth.data.model.toDomain
import com.builtinmedia.hris.features.auth.domain.entities.UserEntities
import com.builtinmedia.hris.features.auth.domain.repositories.AuthRepositories
import javax.inject.Inject

class AuthRepositoriesImpl @Inject constructor(
    private val authRemote: AuthRemoteDataSource,
    private val tokenProvider: TokenProvider
) : AuthRepositories {
    override suspend fun login(
        email: String,
        password: String
    ): Either<ApiException, UserEntities> {
        val result = safeApiCall {
            authRemote.login(LoginRequestModel(email, password))
        }
        return result.map { loginData->
            tokenProvider.saveToken(loginData.token, loginData.token)
            loginData.user.toDomain()
        }

    }

    override suspend fun logout(): Either<ApiException, String> {
        val result = safeApiCallNoData {
            authRemote.logout()
        }
        tokenProvider.clearTokens()
        return result
    }
}