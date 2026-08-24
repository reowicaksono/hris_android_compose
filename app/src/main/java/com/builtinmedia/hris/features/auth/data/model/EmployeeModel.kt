package com.builtinmedia.hris.features.auth.data.model

import com.builtinmedia.hris.features.auth.domain.entities.EmployeeEntities
import com.google.gson.annotations.SerializedName

data class EmployeeModel(

    @field:SerializedName("nik") val nik: String,

    @field:SerializedName("no_hp") val noHp: String,

    @field:SerializedName("jabatan") val jabatan: String,

    @field:SerializedName("nama_lengkap") val namaLengkap: String,

    @field:SerializedName("id") val id: Int,

    @field:SerializedName("departemen") val departemen: String
)

fun EmployeeModel.toDomain(): EmployeeEntities = EmployeeEntities(
    id = id,
    nik = nik,
    namaLengkap = namaLengkap,
    jabatan = jabatan,
    departemen = departemen,
    noHp = noHp
)