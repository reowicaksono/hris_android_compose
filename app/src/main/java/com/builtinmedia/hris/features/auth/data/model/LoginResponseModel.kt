package com.builtinmedia.hris.features.auth.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("user")
    val user: UserModel,
)