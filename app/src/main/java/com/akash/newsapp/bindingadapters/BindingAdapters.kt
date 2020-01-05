package com.akash.newsapp.bindingadapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import com.akash.newsapp.adapters.RecyclerViewBindingAdapter
import com.akash.newsapp.base.BaseRowModel
import com.akash.newsapp.utils.GlimpseCoilTransformation

/**
 * Created by Akash on 2019-09-07
 */
@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    this.load(url) {
        transformations(GlimpseCoilTransformation())
        memoryCachePolicy(CachePolicy.READ_ONLY)
    }
}

@BindingAdapter("listData")
fun RecyclerView.setRowLayoutData(listData: List<BaseRowModel>?) {

    if (listData == null)
        return

    var adapter = this.adapter

    if (adapter == null) {
        adapter = RecyclerViewBindingAdapter(listData)
        this.adapter = adapter
        this.setHasFixedSize(false)
        this.layoutManager = LinearLayoutManager(this.context)
        this.isNestedScrollingEnabled = false
    } else {
        (adapter as RecyclerViewBindingAdapter).setListData(listData)
    }
}

@BindingAdapter("goneIfFalse")
fun View.goneifFalse(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}
