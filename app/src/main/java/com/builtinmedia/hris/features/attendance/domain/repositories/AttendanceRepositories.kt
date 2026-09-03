package com.builtinmedia.hris.features.attendance.domain.repositories

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.attendance.domain.entities.AnnouncementEntities
import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities
import java.io.File

interface AttendanceRepositories {
    suspend fun getHistory(bulan: String?=null): Either<ApiException, List<AttendanceEntities>>
    suspend fun checkIn(
        lat: Double,
        long: Double,
        foto: File?=null
    ) : Either<ApiException, AttendanceEntities>

    suspend fun checkOut(
        lat: Double,
        long: Double,
        foto: File?=null
    ) : Either<ApiException, AttendanceEntities>

    suspend fun getAnnouncements(): Either<ApiException, List<AnnouncementEntities>>
}