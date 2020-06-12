package com.akash.newsapp.utils.extensions

import android.content.res.Resources

/**
 * Created by Akash on 2020-01-27
 */

fun Float.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)
