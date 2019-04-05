package com.akash.newsapp.categoryconstants

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


@Retention(RetentionPolicy.SOURCE)
@StringDef(value = arrayOf(Category.GENERAL,Category.BUSINESS,Category.TECH))
internal annotation class Category {
    companion object {
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val TECH = "technology"
    }
}