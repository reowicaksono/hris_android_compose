package com.builtinmedia.hris.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.format.TextStyle as JavaTextStyle

object Utils {
    fun formatTime(hhmm: String): String {
        return runCatching {
            val (h, m) = hhmm.split(":").map { it.toInt() }
            val period = if (h >= 12) "PM" else "AM"
            val hour12 = when {
                h == 0 -> 12
                h > 12 -> h - 12
                else -> h
            }
            "%02d:%02d %s".format(hour12, m, period)
        }.getOrDefault(hhmm)
    }

    fun formatPublishDate(raw: String?): String {
        if (raw.isNullOrBlank()) return ""
        return runCatching {
            val date = LocalDate.parse(raw, DateTimeFormatter.ISO_LOCAL_DATE)
            "${date.dayOfMonth} ${date.month.getDisplayName(JavaTextStyle.FULL, Locale("id"))} ${date.year}"
        }.getOrDefault(raw)
    }
}