package com.builtinmedia.hris.core.network.mapping

import com.google.gson.annotations.SerializedName

data class PaginatedData<T>(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("data")
    val items: List<T>,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("last_page")
    val lastPage: Int? = null,
    @SerializedName("total")
    val total: Int ?= null
) {
    val isLastPage: Boolean
        get() = lastPage != null && currentPage >= lastPage
}
