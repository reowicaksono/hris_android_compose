package com.builtinmedia.hris.core.network.mapping

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String ?= null,
    @SerializedName("data")
    val data: T ?= null
)
