package com.builtinmedia.hris.features.auth.data.model

import com.builtinmedia.hris.features.auth.domain.entities.UserEntities
import com.google.gson.annotations.SerializedName

data class UserModel(

	@field:SerializedName("role")
	val role: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field: SerializedName("employee")
	val employee: EmployeeModel? = null,

	@field: SerializedName("company")
	val company: CompanyModel? = null,
)

fun UserModel.toDomain(): UserEntities = UserEntities(
	id = id,
	name = name,
	email = email,
	role = role,
	employee = employee?.toDomain(),
	company = company?.toDomain()
)
