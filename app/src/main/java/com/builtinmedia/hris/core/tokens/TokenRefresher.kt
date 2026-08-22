package com.builtinmedia.hris.core.tokens

interface TokenRefresher {
    suspend fun refresh(refreshToken:String): Pair<String, String>?
}