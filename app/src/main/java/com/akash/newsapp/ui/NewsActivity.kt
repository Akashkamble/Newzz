package com.akash.newsapp.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import com.akash.newsapp.R
import com.akash.newsapp.adapters.NewsCategoryAdapter
import com.akash.newsapp.viewmodels.NewsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {
    val TAG = NewsActivity::class.java.simpleName

    val newsViewModel: NewsViewModel by viewModel()
    private lateinit var viewPager: androidx.viewpager.widget.ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPagerAdapter: NewsCategoryAdapter
    private lateinit var toolBar : Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        toolBar = findViewById(R.id.toolbar)
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setUpViewPager()
        setTitleText()
        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                when(position){
                    0->toolBar.title = "General"
                    1->toolBar.title = "Business"
                    2->toolBar.title = "Technology"
                    else->toolBar.title = "General"
                }
                Log.e(TAG,"position :$position")
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun setTitleText() {
        when(viewPager.currentItem){
            0->toolBar.title = "General"
            1 ->toolBar.title = "Business"
            2->toolBar.title = "Technology"
        }

    }

    private fun setUpViewPager() {
        viewPagerAdapter = NewsCategoryAdapter(supportFragmentManager)
        viewPagerAdapter.addFragmnet(GeneralFragment())
        viewPagerAdapter.addFragmnet(BusinessFragment())
        viewPagerAdapter.addFragmnet(TechnologyFragment())
        viewPager.adapter = viewPagerAdapter
    }


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.general -> {
                viewPager.currentItem = 0
                toolBar.title = "General"
                    return@OnNavigationItemSelectedListener true
            }
            R.id.science -> {
                viewPager.currentItem = 1
                toolBar.title = "Business"
                return@OnNavigationItemSelectedListener true
            }
            R.id.technology -> {
                viewPager.currentItem = 2
                toolBar.title = "Technology"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
