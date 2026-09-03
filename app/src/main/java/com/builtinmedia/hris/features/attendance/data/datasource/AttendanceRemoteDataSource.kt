package com.builtinmedia.hris.features.attendance.data.datasource

import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.core.network.mapping.ApiResponse
import com.builtinmedia.hris.core.network.mapping.PaginatedData
import com.builtinmedia.hris.features.attendance.data.model.AnnouncementModel
import com.builtinmedia.hris.features.attendance.data.model.AttendanceModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface AttendanceRemoteDataSource {
    @GET("attendance/history")
    suspend fun getHistory(
        @Query("bulan") bulan: String?
    ): ApiResponse<PaginatedData<AttendanceModel>>

    @Multipart
    @POST("attendance/checkin")
    suspend fun checkIn(
        @Part("lat") lat: RequestBody,
        @Part("lng") long: RequestBody,
        @Part foto: MultipartBody.Part?
    ): ApiResponse<AttendanceModel>

    @Multipart
    @POST("attendance/checkout")
    suspend fun checkOut(
        @Part("lat") lat: RequestBody,
        @Part("lng") long: RequestBody,
        @Part foto: MultipartBody.Part?
    ): ApiResponse<AttendanceModel>

    @GET("announcements")
    suspend fun getAnnouncements(): ApiResponse<PaginatedData<AnnouncementModel>>
}