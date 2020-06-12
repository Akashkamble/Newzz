package com.akash.newsapp.base

/**
 * Created by Akash on 2019-09-07
 */
abstract class BaseRowModel {

    val TAG: String = javaClass.name

    var layoutID: Int = 0
        protected set

    init {
        setLayoutID()
    }

    abstract fun setLayoutID()
}
