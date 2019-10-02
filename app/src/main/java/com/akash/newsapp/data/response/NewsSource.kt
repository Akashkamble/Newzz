package com.akash.newsapp.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsSource (
    @SerializedName("name")
    val name: String?
)
