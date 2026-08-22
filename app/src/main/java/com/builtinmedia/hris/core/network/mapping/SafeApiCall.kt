package com.builtinmedia.hris.core.network.mapping

import com.builtinmedia.hris.core.errors.ErrorMapper

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.builtinmedia.hris.core.errors.ApiException

suspend fun <T> safeApiCall(apiCall: suspend () -> ApiResponse<T>): Either<ApiException, T> {
    return try {
        val response = apiCall()
        when {
            !response.success -> ApiException.BadRequest(response.message ?: "Request Gagal").left()
            response.data == null -> ApiException.EmptyData().left()
            else -> response.data.right()
        }
    } catch (e: Exception) {
        ErrorMapper.map(e).left()
    }
}

suspend fun safeApiCallNoData(apiCall: suspend () -> ApiResponse<Unit?>): Either<ApiException, String> {
    return try {
        val response = apiCall()
        if (response.success) {
            (response.message ?: "Berhasil").right()
        } else {
            ApiException.BadRequest(response.message ?: "Request Gagal").left()
        }
    } catch (e: Exception) {
        ErrorMapper.map(e).left()
    }
}