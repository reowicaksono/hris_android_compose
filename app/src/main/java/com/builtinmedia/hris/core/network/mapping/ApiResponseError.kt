package com.builtinmedia.hris.core.network.mapping

import com.google.gson.annotations.SerializedName

data class ApiResponseError(
    @SerializedName("message")
    val message: String? = null,

    @SerializedName("errors")
    val errors: Map<String, List<String>>? = null
)
