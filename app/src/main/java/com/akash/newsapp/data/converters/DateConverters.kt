package com.akash.newsapp.data.converters

import androidx.room.TypeConverter
import androidx.annotation.NonNull
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateConverters {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    fun getDate(isoDateString : String) : ZonedDateTime{
        return ZonedDateTime.parse(isoDateString).withZoneSameInstant(ZoneId.systemDefault())
//        return ZonedDateTime.now()
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }

}