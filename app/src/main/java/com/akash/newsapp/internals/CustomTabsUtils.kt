package com.akash.newsapp.internals

import android.app.Activity
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.akash.newsapp.R

internal class CustomTabsUtils {
    companion object {
        private lateinit var builder: CustomTabsIntent
        fun launch(activity: Activity, url: String, @ColorRes color: Int) {
            builder = CustomTabsIntent.Builder()
                .setToolbarColor(
                    ContextCompat.getColor(activity.baseContext, color)
                )
                .setShowTitle(true)
                .setStartAnimations(activity.baseContext, R.anim.slide_in_right, R.anim.slide_out_left)
                .setExitAnimations(
                    activity.baseContext,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                .build()
            builder.launchUrl(activity, Uri.parse(url))
        }
    }
}