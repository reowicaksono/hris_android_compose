package com.builtinmedia.hris.core.tokens

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenProvider: TokenProvider,
    private val tokenRefresher: TokenRefresher
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2){
            tokenProvider.clearTokens()
            return null
        }

        val refreshToken = tokenProvider.getRefreshToken() ?: return null

        return runBlocking {
            val newTokens = tokenRefresher.refresh(refreshToken)
            if (newTokens != null) {
                tokenProvider.saveToken(newTokens.first, newTokens.second)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${newTokens.first}")
                    .build()
            } else {
                tokenProvider.clearTokens()
                null
            }
        }
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) { count++; prior = prior.priorResponse }
        return count
    }
}