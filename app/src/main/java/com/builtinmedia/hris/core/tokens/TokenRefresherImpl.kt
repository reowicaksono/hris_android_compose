package com.builtinmedia.hris.core.tokens

import com.builtinmedia.hris.features.auth.data.datasource.AuthRemoteDataSource
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class TokenRefresherImpl @Inject constructor(
    private val authRemoteDataSource: Provider<AuthRemoteDataSource>
): TokenRefresher{
    override suspend fun refresh(refreshToken: String): Pair<String, String>? {
        return null
//        return try{
//            val result = authRemoteDataSource.get().refreshToken(RefreshTokenRequest(refreshToken))
//            val data = result.data
//            if (result.success && data != null){
//                data.accessToken to data.refreshToken
//            } else null
//        }catch (e: Exception){
//            null
//        }
    }

}