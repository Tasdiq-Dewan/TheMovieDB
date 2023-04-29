package com.tasdiqdewan.utils

import com.tasdiqdewan.utils.Constants.SIMPLE_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.Locale


fun String.convertToDateFormat(dateFormat: String = SIMPLE_DATE_FORMAT): String{
    var simpleDateFormatter = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.ENGLISH)
    val date = simpleDateFormatter.parse(this)
    dateFormat?.takeIf { it != SIMPLE_DATE_FORMAT }?.let {
        simpleDateFormatter = SimpleDateFormat(it, Locale.ENGLISH)
    }
    return date?.let { simpleDateFormatter.format(it) } ?: ""
}