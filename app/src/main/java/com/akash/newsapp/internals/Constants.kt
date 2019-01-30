package com.akash.newsapp.internals

import org.koin.standalone.KoinComponent
import org.koin.standalone.getProperty

class Constants: KoinComponent {
    val API_KEY : String
        get() = getProperty("api_key")
    val COUNTRY : String
        get() = "in"
}