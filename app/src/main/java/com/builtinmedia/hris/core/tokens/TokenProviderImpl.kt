package com.builtinmedia.hris.core.tokens

import androidx.datastore.core.DataStore
import com.builtinmedia.hris.core.constants.Constants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenProviderImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): TokenProvider {
    @Volatile private var cachedAccessToken: String? =null
    @Volatile private var cachedRefreshToken: String? =null

    //run blocking
    init {
        runBlocking {
            val prefs = dataStore.data.first()
            cachedAccessToken = prefs[Constants.ACCESS_TOKEN]
            cachedRefreshToken = prefs[Constants.REFRESH_TOKEN]

        }
    }

    override fun getAccessToken(): String? = cachedAccessToken

    override fun getRefreshToken(): String? = cachedRefreshToken

    override fun saveToken(accessToken: String, refreshToken: String) {
        cachedAccessToken = accessToken
        cachedRefreshToken = refreshToken

        runBlocking {
            dataStore.edit { prefs ->
                prefs[Constants.ACCESS_TOKEN] = accessToken
                prefs[Constants.REFRESH_TOKEN] = refreshToken
            }
        }
    }

    override fun clearTokens() {
        cachedAccessToken = null
        cachedRefreshToken = null
        runBlocking {
            dataStore.edit { it.clear() }
        }
    }
}