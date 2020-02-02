package com.akash.newsapp.ui.bindingadapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.akash.newsapp.ui.adapters.RecyclerViewBindingAdapter
import com.akash.newsapp.base.BaseRowModel
import com.akash.newsapp.utils.extensions.toPx
import com.akash.newsapp.ui.itemdecoration.DividerItemDecoration
import glimpse.coil.GlimpseTransformation

/**
 * Created by Akash on 2019-09-07
 */
@BindingAdapter("loadUrl", "cornerRadius", requireAll = true)
fun ImageView.loadUrl(url: String, radius: Float) {
    this.load(url) {
        transformations(GlimpseTransformation(), RoundedCornersTransformation(radius.toPx()))
        memoryCachePolicy(CachePolicy.READ_ONLY)
    }
}

@BindingAdapter("listData", "setDecorator", requireAll = false)
fun RecyclerView.setRowLayoutData(listData: List<BaseRowModel>?, decorator: Drawable?) {

    if (listData == null)
        return

    var adapter = this.adapter

    if (adapter == null) {
        adapter = RecyclerViewBindingAdapter(listData)
        this.adapter = adapter
        decorator?.let {
            this.addItemDecoration(DividerItemDecoration(it))
        }
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
