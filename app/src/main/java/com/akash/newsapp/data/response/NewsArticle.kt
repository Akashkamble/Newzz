package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class NewsArticle(
    val publishedAt: String,

    val source: NewsSource,

    val title: String,

    val description: String?,

    val url: String,

    val urlToImage: String?
)
