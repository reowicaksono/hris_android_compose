package com.builtinmedia.hris.features.auth.domain.entities

data class UserEntities(
	val role: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val email: String? = null,
	val employee: EmployeeEntities? = null,
	val company: CompanyEntities? = null
)