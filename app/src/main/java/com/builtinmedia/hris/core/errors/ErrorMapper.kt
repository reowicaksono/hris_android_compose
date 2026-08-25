package com.builtinmedia.hris.core.errors

import com.builtinmedia.hris.core.network.mapping.ApiResponseError
import com.google.gson.Gson
import okio.IOException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorMapper {

    private val gson = Gson()
    fun map(e: Exception): ApiException {
        return when (e) {
            is HttpException -> when (e.code()) {
                401 -> ApiException.Unauthorized()
                400, 422 -> ApiException.BadRequest(parseErrorMesage(e) ?: "Permintaan tidak valid")
                in 500..599 -> ApiException.ServerError(code = e.code())
                else -> ApiException.Unknown(e.message ?: "Terjadi kesalahan tidak diketahui")
            }

            is UnknownHostException -> ApiException.NoInternet()
            is SocketTimeoutException -> ApiException.Timeout()
            is IOException -> ApiException.NoInternet()
            else -> ApiException.Unknown(e.message ?: "Terjadi kesalahan tidak diketahui")
        }
    }

    fun parseErrorMesage(e: HttpException): String? {
        return try {
            val body = e.response()
                ?.errorBody()
                ?.string()
                ?: return null

            val response = gson.fromJson(
                body,
                ApiResponseError::class.java
            )

            response.message
        } catch (ex: Exception) {
            null
        }
    }
}