package com.builtinmedia.hris.features.attendance.domain.usecase

import arrow.core.Either
import com.builtinmedia.hris.core.constants.Constants
import com.builtinmedia.hris.core.errors.ApiException
import com.builtinmedia.hris.features.attendance.domain.entities.AnnouncementEntities
import com.builtinmedia.hris.features.attendance.domain.repositories.AttendanceRepositories
import javax.inject.Inject

class GetLatestAnnouncementUseCase @Inject constructor(
    private val attendanceRepositories: AttendanceRepositories
) {
    suspend operator fun invoke(limit: Int = Constants.DEFAULT_LIMIT) : Either<ApiException, List<AnnouncementEntities>>{
        return attendanceRepositories.getAnnouncements().map { announcements ->
            announcements
                .sortedByDescending { it.tanggalPublish.orEmpty() }
                .take(limit)
        }
    }
}