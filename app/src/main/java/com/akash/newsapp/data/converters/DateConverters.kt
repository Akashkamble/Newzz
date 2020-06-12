package com.akash.newsapp.data.converters

import androidx.annotation.Keep
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

@Keep
object DateConverters {

    fun getDate(isoDateString: String): ZonedDateTime {
        return ZonedDateTime.parse(isoDateString).withZoneSameInstant(ZoneId.systemDefault())
    }
}
