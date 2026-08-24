package com.builtinmedia.hris.features.auth.data.datasource

import com.builtinmedia.hris.core.network.mapping.ApiResponse
import com.builtinmedia.hris.features.auth.data.model.LoginRequestModel
import com.builtinmedia.hris.features.auth.data.model.LoginResponseModel
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRemoteDataSource {
    @POST("login")
    suspend fun login(@Body request: LoginRequestModel): ApiResponse<LoginResponseModel>

    @POST("logout")
    suspend fun logout(): ApiResponse<Unit?>
}