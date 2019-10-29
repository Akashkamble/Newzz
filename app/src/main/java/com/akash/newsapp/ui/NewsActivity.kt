package com.akash.newsapp.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.akash.newsapp.NewsApplication
import com.akash.newsapp.R
import com.akash.newsapp.adapters.NewsCategoryAdapter
import com.akash.newsapp.categoryconstants.Category
import com.akash.newsapp.utils.PreferenceHelper.Companion.IS_DARK_MODE
import com.akash.newsapp.utils.PreferenceHelper.Companion.NOT_DARK_MODE
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*

class NewsActivity : AppCompatActivity() {
    val TAG = NewsActivity::class.java.simpleName

    private lateinit var viewPagerAdapter: NewsCategoryAdapter
    private lateinit var viewPager: ViewPager
    private lateinit var toolBar: Toolbar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBarTitle: TextView
    private lateinit var themeToggle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nightMode = NewsApplication.prefs!!.isDark



        setTheme(

            when (nightMode) {
                NOT_DARK_MODE ->
                    R.style.AppTheme

                IS_DARK_MODE ->
                    R.style.DarkTheme
                else -> {

                    val currentNightMode =
                        resources!!.configuration!!.uiMode and Configuration.UI_MODE_NIGHT_MASK

                    when (currentNightMode) {
                        Configuration.UI_MODE_NIGHT_YES -> {
                            R.style.DarkTheme
                        }
                        Configuration.UI_MODE_NIGHT_NO -> {
                            R.style.AppTheme
                        }
                        else -> R.style.AppTheme
                    }
                }

            }


        )
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        toolBar = findViewById(R.id.toolbar)
        toolBarTitle = toolBar.toolbarTitle
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        themeToggle = toolBar.iv_theme_toggle

        themeToggle.setOnClickListener {
            toggleTheme()
        }
        setUpViewPager()
        setTitleText()
        setUpThemeToggleImage()
        viewPager.addOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> toolBarTitle.text = TITLE_GENERAL
                    1 -> toolBarTitle.text = TITLE_BUSINESS
                    2 -> toolBarTitle.text = TITLE_TECHNOLOGY
                    else -> toolBarTitle.text = TITLE_GENERAL
                }
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun toggleTheme() {
        restartActivity()
    }

    private fun restartActivity() {
        if (NewsApplication.prefs!!.isDark == IS_DARK_MODE) {
            NewsApplication.prefs!!.isDark = NOT_DARK_MODE
        } else {
            NewsApplication.prefs!!.isDark = IS_DARK_MODE
        }
        NewsApplication.prefs!!.currentPage = viewPager.currentItem
        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }

    private fun setUpThemeToggleImage() {
        if (NewsApplication.prefs!!.isDark == IS_DARK_MODE) {
            themeToggle.setImageResource(R.drawable.ic_dark)
        } else {
            themeToggle.setImageResource(R.drawable.ic_light)
        }
    }

    private fun setTitleText() {
        when (viewPager.currentItem) {
            0 -> toolBarTitle.text = TITLE_GENERAL
            1 -> toolBarTitle.text = TITLE_BUSINESS
            2 -> toolBarTitle.text = TITLE_TECHNOLOGY
        }

    }

    private fun setUpViewPager() {
        viewPagerAdapter = NewsCategoryAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.GENERAL))
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.BUSINESS))
        viewPagerAdapter.addFragment(ArticleFragment.newInstance(Category.TECH))
        viewPager.adapter = viewPagerAdapter
        val storedPageId = NewsApplication.prefs!!.currentPage
        viewPager.currentItem = storedPageId
        bottomNavigationView.selectedItemId = getSelectedItemId(storedPageId)
    }

    private fun getSelectedItemId(pageId: Int) = when (pageId) {
        0 -> R.id.general
        1 -> R.id.science
        2 -> R.id.technology
        else -> R.id.general
    }


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.general -> {
                    viewPager.currentItem = 0
                    toolBarTitle.text = TITLE_GENERAL
                    return@OnNavigationItemSelectedListener true
                }
                R.id.science -> {
                    viewPager.currentItem = 1
                    toolBarTitle.text = TITLE_BUSINESS
                    return@OnNavigationItemSelectedListener true
                }
                R.id.technology -> {
                    viewPager.currentItem = 2
                    toolBarTitle.text = TITLE_TECHNOLOGY
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
