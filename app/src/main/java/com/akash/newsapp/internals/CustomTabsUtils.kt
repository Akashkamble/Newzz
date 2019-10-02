package com.akash.newsapp.internals

import android.app.Activity
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.akash.newsapp.R

internal class CustomTabsUtils {
    companion object {
        private var builder: CustomTabsIntent? = null
        private var builderDark: CustomTabsIntent? = null

        fun launch(activity: Activity, url: String, isDark: Boolean) {
            if (isDark) {
                if (builderDark == null) {
                    builderDark = CustomTabsIntent.Builder()
                        .setToolbarColor(
                            ContextCompat.getColor(activity.baseContext, R.color.colorPrimary_dark)
                        )
                        .setShowTitle(true)
                        .setStartAnimations(activity.baseContext, R.anim.slide_in_right, R.anim.slide_out_left)
                        .setExitAnimations(
                            activity.baseContext,
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right
                        )
                        .build()
                }
                builderDark?.launchUrl(activity, Uri.parse(url))
            } else {
                if (builder == null) {
                    builder = CustomTabsIntent.Builder()
                        .setToolbarColor(
                            ContextCompat.getColor(activity.baseContext, R.color.colorPrimary)
                        )
                        .setShowTitle(true)
                        .setStartAnimations(activity.baseContext, R.anim.slide_in_right, R.anim.slide_out_left)
                        .setExitAnimations(
                            activity.baseContext,
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right
                        )
                        .build()
                }
                builder?.launchUrl(activity, Uri.parse(url))
            }
        }
    }
}