package com.akash.newsapp.internals

import com.akash.newsapp.BuildConfig

object Constants {
    val API_KEY: String
        get() = BuildConfig.API_KEY
    val COUNTRY: String
        get() = "in"
}