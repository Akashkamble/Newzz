package com.akash.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.akash.newsapp.R
import com.akash.newsapp.adapters.NewsCategoryAdapter
import com.akash.newsapp.categoryconstants.Category
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {
    val TAG = NewsActivity::class.java.simpleName

    private lateinit var viewPagerAdapter: NewsCategoryAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolBar: Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        toolBar = findViewById(R.id.toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setUpViewPager()
        setTitleText()
        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> toolBar.title = TITLE_GENERAL
                    1 -> toolBar.title = TITLE_BUSINESS
                    2 -> toolBar.title = TITLE_TECHNOLOGY
                    else -> toolBar.title = TITLE_GENERAL
                }
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun setTitleText() {
        when (viewPager.currentItem) {
            0 -> toolBar.title = TITLE_GENERAL
            1 -> toolBar.title = TITLE_BUSINESS
            2 -> toolBar.title = TITLE_TECHNOLOGY
        }

    }

    private fun setUpViewPager() {
        viewPagerAdapter = NewsCategoryAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.GENERAL))
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.BUSINESS))
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.TECH))
        viewPager.adapter = viewPagerAdapter
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.general -> {
                viewPager.currentItem = 0
                toolBar.title = TITLE_GENERAL
                return@OnNavigationItemSelectedListener true
            }
            R.id.science -> {
                viewPager.currentItem = 1
                toolBar.title = TITLE_BUSINESS
                return@OnNavigationItemSelectedListener true
            }
            R.id.technology -> {
                viewPager.currentItem = 2
                toolBar.title = TITLE_TECHNOLOGY
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    companion object {
        const val TITLE_GENERAL = "General"
        const val TITLE_BUSINESS = "Business"
        const val TITLE_TECHNOLOGY = "Technology"
    }
}
