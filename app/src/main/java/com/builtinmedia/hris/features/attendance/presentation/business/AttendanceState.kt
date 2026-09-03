package com.builtinmedia.hris.features.attendance.presentation.business

import com.builtinmedia.hris.features.attendance.domain.entities.AnnouncementEntities
import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities

data class AttendanceState(
    val isLoading: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String? = null,

    val today: AttendanceEntities? =null,
    val workingDurationText: String = "",

    val isAnnouncementLoading: Boolean = false,
    val announcements: List<AnnouncementEntities> = emptyList()
){
    val isCheckedIn: Boolean
        get() =  today?.isCurrentlyWorking == true
}
