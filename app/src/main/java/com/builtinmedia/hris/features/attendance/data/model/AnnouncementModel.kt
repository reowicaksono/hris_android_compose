package com.builtinmedia.hris.features.attendance.data.model

import com.builtinmedia.hris.features.attendance.domain.entities.AnnouncementEntities
import com.google.gson.annotations.SerializedName

data class AnnouncementModel(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("judul")
    val judul: String? = null,

    @field:SerializedName("isi")
    val isi: String? = null,

    @field:SerializedName("target_departemen")
    val targetDepartemen: String? = null,

    @field:SerializedName("tanggal_publish")
    val tanggalPublish: String? = null
)

fun AnnouncementModel.toDomain(): AnnouncementEntities = AnnouncementEntities(
    id = id,
    judul = judul,
    isi = isi,
    targetDepartemen = targetDepartemen,
    tanggalPublish = tanggalPublish
)
