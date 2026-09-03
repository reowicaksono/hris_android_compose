package com.builtinmedia.hris.features.attendance.domain.usecase

import arrow.core.Either
import arrow.core.left
import com.builtinmedia.hris.core.constants.Constants
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities
import com.builtinmedia.hris.features.attendance.domain.repositories.AttendanceRepositories
import java.io.File
import javax.inject.Inject

class CheckOutUseCase @Inject constructor(
    private val attendanceRepositories: AttendanceRepositories
){
    suspend operator fun invoke(
        lat: Double,
        long: Double,
        foto: File? = null
    ): Either<ApiException, AttendanceEntities> {
        if (lat == null || lat == 0.0 || long == null || long == 0.0) {
            return ApiException.BadRequest("Lokasi tidak ditemukan, aktifkan GPS terlebih dahulu")
                .left()
        }

        if (lat !in -90.0..90.0 || long !in -180.0..180.0) {
            return ApiException.BadRequest("Koordinat tidak valid").left()
        }
        if (foto != null && foto.length() > Constants.MAX_PHOTO_SIZE_BYTES) {
            return ApiException.BadRequest("Ukuran foto/gambar maksimal 2Mb").left()
        }

        return attendanceRepositories.checkIn(lat, long)
    }
}