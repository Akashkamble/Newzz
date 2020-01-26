package com.akash.newsapp.adapters

class NewsCategoryAdapter(fragmentManager: androidx.fragment.app.FragmentManager) :
    androidx.fragment.app.FragmentPagerAdapter(
        fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ) {

    private val fragmentList: MutableList<androidx.fragment.app.Fragment> = ArrayList()

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: androidx.fragment.app.Fragment) {
        fragmentList.add(fragment)
    }
}
