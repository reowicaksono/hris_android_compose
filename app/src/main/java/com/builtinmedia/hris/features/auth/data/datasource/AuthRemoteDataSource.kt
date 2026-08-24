package com.builtinmedia.hris.features.auth.data.datasource

import com.builtinmedia.hris.core.network.mapping.ApiResponse
import com.builtinmedia.hris.features.auth.data.dto.LoginRequest
import com.builtinmedia.hris.features.auth.data.dto.LoginResponse
import com.builtinmedia.hris.features.auth.data.model.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthRemoteDataSource {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<LoginResponse>

    @POST("logout")
    suspend fun logout(): ApiResponse<Unit?>

    @GET("me")
    suspend fun getUserData(): ApiResponse<UserModel>
}