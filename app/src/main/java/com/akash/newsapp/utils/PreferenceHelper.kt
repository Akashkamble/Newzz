package com.akash.newsapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Akash on 2019-10-02
 */

class PreferenceHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var isDArk: Boolean
        get() = prefs.getBoolean(IS_DARK, false)
        set(value) = prefs.edit().putBoolean(IS_DARK, value).apply()

    companion object {
        const val PREFS_FILENAME = "com.akash.newsapp"
        const val IS_DARK = "is_dark"
    }
}