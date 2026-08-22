package com.builtinmedia.hris.core.tokens

interface TokenProvider {
    fun getAccessToken(): String?
    fun getRefreshToken(): String?
    fun saveToken(accessToken: String, refreshToken: String)
    fun clearTokens()
}