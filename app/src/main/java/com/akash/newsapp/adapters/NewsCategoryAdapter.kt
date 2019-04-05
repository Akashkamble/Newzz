package com.akash.newsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NewsCategoryAdapter(fragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {
    private val fragmentList : MutableList<androidx.fragment.app.Fragment> = ArrayList()
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragmnet(fragmant : androidx.fragment.app.Fragment){
        fragmentList.add(fragmant)
    }
}