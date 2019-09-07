package com.akash.newsapp.data.converters

import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

object DateConverters {

    fun getDate(isoDateString: String): ZonedDateTime {
        return ZonedDateTime.parse(isoDateString).withZoneSameInstant(ZoneId.systemDefault())
    }
}