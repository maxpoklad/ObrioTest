package com.poklad.obriotest.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateConverter @Inject constructor() {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    fun convertLongToDateTime(time: Long): String {
        return dateFormat.format(Date(time))
    }
}