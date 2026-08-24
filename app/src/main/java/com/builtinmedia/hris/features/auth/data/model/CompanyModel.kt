package com.builtinmedia.hris.features.auth.data.model
import com.builtinmedia.hris.features.auth.domain.entities.CompanyEntities
import com.builtinmedia.hris.features.auth.domain.entities.EmployeeEntities
import com.google.gson.annotations.SerializedName

data class CompanyModel(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("address")
    val address: String? = null
)

fun CompanyModel.toDomain():CompanyEntities = CompanyEntities(
    id = id,
    name = name,
    address = address
)