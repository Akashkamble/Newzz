package com.akash.newsapp

import android.app.Application
import com.akash.newsapp.injection.applicationModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

class NewsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin(this, listOf(applicationModule),loadPropertiesFromFile = true)
    }
}