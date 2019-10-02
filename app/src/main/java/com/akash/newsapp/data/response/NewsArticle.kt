package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsArticle(
    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("source")
    val source: NewsSource,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("urlToImage")
    val urlToImage: String?
)