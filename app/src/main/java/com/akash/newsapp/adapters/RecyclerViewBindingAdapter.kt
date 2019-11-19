package com.akash.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.akash.newsapp.BR
import com.akash.newsapp.R
import com.akash.newsapp.base.BaseRowModel

/**
 * Created by Akash on 2019-09-07
 */

class RecyclerViewBindingAdapter(var data: List<BaseRowModel>) :
    RecyclerView.Adapter<RecyclerViewBindingAdapter.ViewHolder>() {

    fun setListData(list: List<BaseRowModel>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val dataViewBinding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, i, viewGroup, false)
        return ViewHolder(dataViewBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(data[i])
        setAnimation(viewHolder.itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].layoutID
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.clearAnimations()
        super.onViewDetachedFromWindow(holder)
    }

    private fun setAnimation(view: View) {
        val animation =
            AnimationUtils.loadAnimation(view.context, R.anim.item_animation_slide_from_right)
        view.startAnimation(animation)
    }

    class ViewHolder(private val dataViewBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(dataViewBinding.root) {

        fun bind(dataModel: Any) {
            dataViewBinding.setVariable(BR.vm, dataModel)
            dataViewBinding.executePendingBindings()
        }

        fun clearAnimations() {
            dataViewBinding.root.animation = null
        }
    }
}
