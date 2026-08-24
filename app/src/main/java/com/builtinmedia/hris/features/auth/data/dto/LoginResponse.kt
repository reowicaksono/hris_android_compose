package com.builtinmedia.hris.features.auth.data.dto

import com.builtinmedia.hris.features.auth.data.model.UserModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("user")
    val user: UserModel,
)