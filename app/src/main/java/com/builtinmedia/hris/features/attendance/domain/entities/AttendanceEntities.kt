package com.builtinmedia.hris.features.attendance.domain.entities

data class AttendanceEntities(
    val id: Int?=null,
    val employeeId: Int?=null,
    val tanggal: String?=null,
    val jamMasuk: String?=null,
    val jamPulang: String?=null,
    val status: String?=null,
    val latMasuk: Double?=null,
    val longMasuk: Double?=null,
    val fotoMasuk: String?=null,
    val locationName: String?=null
){
    val isCurrentlyWorking: Boolean
        get() = !jamMasuk.isNullOrBlank() && jamPulang.isNullOrBlank()
}
