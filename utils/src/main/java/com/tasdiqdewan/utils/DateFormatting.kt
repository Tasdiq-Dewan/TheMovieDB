package com.tasdiqdewan.utils

import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT_ISO
import java.text.SimpleDateFormat
import java.util.Locale


fun String.convertToDateFormat(dateFormat: String = SIMPLE_DATE_FORMAT_ISO): String{
    var simpleDateFormatter = SimpleDateFormat(SIMPLE_DATE_FORMAT_ISO, Locale.ENGLISH)
    val date = simpleDateFormatter.parse(this)
    dateFormat?.takeIf { it != SIMPLE_DATE_FORMAT_ISO }?.let {
        simpleDateFormatter = SimpleDateFormat(it, Locale.ENGLISH)
    }
    return date?.let { simpleDateFormatter.format(it) } ?: ""
}