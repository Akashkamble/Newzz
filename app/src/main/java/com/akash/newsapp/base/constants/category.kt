package com.akash.newsapp.base.constants

import androidx.annotation.StringDef


@Retention(AnnotationRetention.SOURCE)
@StringDef(value = [Category.GENERAL, Category.BUSINESS, Category.TECH])
internal annotation class Category {
    companion object {
        const val GENERAL = "general"
        const val BUSINESS = "business"
        const val TECH = "technology"
    }
}