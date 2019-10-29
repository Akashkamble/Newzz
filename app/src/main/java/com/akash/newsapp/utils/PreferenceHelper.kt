package com.akash.newsapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Akash on 2019-10-02
 */

class PreferenceHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var isDark: String
        get() = prefs.getString(IS_DARK, UNDEFINED_DARK_MODE)!!
        set(value) = prefs.edit().putString(IS_DARK, value).apply()

    var currentPage: Int
        get() = prefs.getInt(CURRENT_PAGE, 0)
        set(value) = prefs.edit().putInt(CURRENT_PAGE, value).apply()

    companion object {
        const val PREFS_FILENAME = "com.akash.newsapp"
        const val IS_DARK = "is_dark"
        const val CURRENT_PAGE = "current-page"

        const val IS_DARK_MODE = "is_dark_mode"
        const val NOT_DARK_MODE = "not_dark_mode"
        const val UNDEFINED_DARK_MODE = "undefined_dark_mode"
    }
}
