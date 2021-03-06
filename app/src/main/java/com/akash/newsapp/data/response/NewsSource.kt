package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class NewsSource(
    val name: String?
)
