package com.builtinmedia.hris.features.attendance.domain.usecase

import arrow.core.Either
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities
import com.builtinmedia.hris.features.attendance.domain.repositories.AttendanceRepositories
import javax.inject.Inject

class GetAttendanceHistoryUseCase @Inject constructor(
    private val attendanceRepositories: AttendanceRepositories
) {
    suspend operator fun invoke(bulan: String?=null): Either<ApiException, List<AttendanceEntities>>{
        return attendanceRepositories.getHistory()
    }
}