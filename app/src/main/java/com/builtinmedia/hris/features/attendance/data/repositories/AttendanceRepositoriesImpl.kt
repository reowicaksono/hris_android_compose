package com.builtinmedia.hris.features.attendance.data.repositories

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.core.extensions.toMultipart
import com.builtinmedia.hris.core.extensions.toRequestBodyPart
import com.builtinmedia.hris.core.network.mapping.safeApiCall
import com.builtinmedia.hris.features.attendance.data.datasource.AttendanceRemoteDataSource
import com.builtinmedia.hris.features.attendance.data.model.toDomain
import com.builtinmedia.hris.features.attendance.domain.entities.AnnouncementEntities
import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities
import com.builtinmedia.hris.features.attendance.domain.repositories.AttendanceRepositories
import java.io.File
import javax.inject.Inject

class AttendanceRepositoriesImpl @Inject constructor(
    private val attendanceRemoteDataSource: AttendanceRemoteDataSource
): AttendanceRepositories{
    override suspend fun getHistory(bulan: String?): Either<ApiException, List<AttendanceEntities>> {
        return safeApiCall { attendanceRemoteDataSource.getHistory(bulan) }
            .map { paginated -> paginated.items.map { it.toDomain() } }
    }

    override suspend fun checkIn(
        lat: Double,
        long: Double,
        foto: File?
    ): Either<ApiException, AttendanceEntities> {
        val result = safeApiCall {
            attendanceRemoteDataSource.checkIn(
                lat = lat.toRequestBodyPart(),
                long = long.toRequestBodyPart(),
                foto = foto?.toMultipart("foto")
            )
        }
        return result.map { it.toDomain() }
    }

    override suspend fun checkOut(
        lat: Double,
        long: Double,
        foto: File?
    ): Either<ApiException, AttendanceEntities> {
        val result = safeApiCall {
            attendanceRemoteDataSource.checkOut(
                lat = lat.toRequestBodyPart(),
                long = long.toRequestBodyPart(),
                foto = foto?.toMultipart("foto")
            )
        }
        return result.map { it.toDomain() }
    }

    override suspend fun getAnnouncements(): Either<ApiException, List<AnnouncementEntities>> {
        return safeApiCall { attendanceRemoteDataSource.getAnnouncements() }. map { paginated -> paginated.items.map{it.toDomain()} }
    }
}