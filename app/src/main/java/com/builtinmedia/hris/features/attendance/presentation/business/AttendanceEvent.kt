package com.builtinmedia.hris.features.attendance.presentation.business

import java.io.File

sealed interface AttendanceEvent {
    data object LoadData: AttendanceEvent
    data object Refresh : AttendanceEvent
    data class CheckIn(val lat: Double, val long: Double, val foto: File? = null) : AttendanceEvent
    data class CheckOut(val lat: Double, val long: Double, val foto: File? =null) : AttendanceEvent
    data object ErrorShown : AttendanceEvent
}