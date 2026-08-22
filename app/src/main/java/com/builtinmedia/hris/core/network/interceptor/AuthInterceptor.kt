package com.builtinmedia.hris.core.network.interceptor

import com.builtinmedia.hris.core.tokens.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = tokenProvider.getAccessToken()

        val request = if (!token.isNullOrEmpty() && !original.url.encodedPath.contains("/auth/")){
            original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }else original
        return chain.proceed(request)
    }
}