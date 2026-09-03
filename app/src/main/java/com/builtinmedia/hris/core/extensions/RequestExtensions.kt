package com.builtinmedia.hris.core.extensions

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

fun Any.toRequestBodyPart(): RequestBody {
    return toString().toRequestBody("text/plain".toMediaType())
}

fun File.toMultipart(
    fieldName: String
): MultipartBody.Part{
    val requestBody = asRequestBody("image/*".toMediaTypeOrNull())

    return MultipartBody.Part.createFormData(
        name = fieldName,
        filename = name,
        body = requestBody
    )
}