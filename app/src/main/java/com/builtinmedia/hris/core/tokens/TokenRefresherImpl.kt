package com.builtinmedia.hris.core.tokens

import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class TokenRefresherImpl @Inject constructor(
    private val authApiServiceProvider: Provider<AuthApiService>
): TokenRefresher{
    override suspend fun refresh(refreshToken: String): Pair<String, String>? {
        return try{
            val result = authApiServiceProvider.get().refreshToken(RefreshTokenRequest(refreshToken))
            val data = result.data
            if (result.success && data != null){
                data.accessToken to data.refreshToken
            } else null
        }catch (e: Exception){
            null
        }
    }

}