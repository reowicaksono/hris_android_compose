package com.builtinmedia.hris.core.errors

sealed class ApiException(message: String) : Exception(message) {
    data class Unauthorized(val msg: String = "Sesi berakhir, silahkan login ulang") : ApiException(msg)
    data class BadRequest(val msg: String) : ApiException(msg)
    data class ServerError(val code: Int = 500, val msg: String = "Terjadi kesalahan pada server") : ApiException(msg)
    data class NoInternet(val msg: String = "Tidak ada koneksi internet") : ApiException(msg)
    data class Timeout(val msg: String = "Koneksi timeout") : ApiException(msg)
    data class EmptyData(val msg: String = "Data tidak ditemukan") : ApiException(msg)
    data class Unknown(val msg: String = "Terjadi kesalahan tidak diketahui") : ApiException(msg)
}