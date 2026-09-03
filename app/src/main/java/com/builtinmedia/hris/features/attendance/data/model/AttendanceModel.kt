package com.builtinmedia.hris.features.attendance.data.model

import com.builtinmedia.hris.features.attendance.domain.entities.AttendanceEntities
import com.google.gson.annotations.SerializedName

data class AttendanceModel(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("employee_id")
    val employeeId: Int? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("jam_masuk")
    val jamMasuk: String? = null,

    @field:SerializedName("jam_pulang")
    val jamPulang: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("lat_masuk")
    val latMasuk: Double? = null,

    @field:SerializedName("lng_masuk")
    val longMasuk: Double? = null,

    @field:SerializedName("foto_masuk")
    val fotoMasuk: String? = null
)

fun AttendanceModel.toDomain(): AttendanceEntities = AttendanceEntities(
    id = id,
    employeeId = employeeId,
    tanggal = tanggal,
    jamMasuk = jamMasuk,
    jamPulang = jamPulang,
    status = status,
    latMasuk = latMasuk,
    longMasuk = longMasuk,
    fotoMasuk = fotoMasuk
)
