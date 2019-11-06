package com.akash.newsapp.ui

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.akash.newsapp.NewsApplication
import com.akash.newsapp.R
import com.akash.newsapp.adapters.NewsCategoryAdapter
import com.akash.newsapp.categoryconstants.Category
import com.akash.newsapp.databinding.ActivityMainBinding
import com.akash.newsapp.utils.PreferenceHelper.Companion.IS_DARK_MODE
import com.akash.newsapp.utils.PreferenceHelper.Companion.NOT_DARK_MODE
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: NewsCategoryAdapter
    private lateinit var binding: ActivityMainBinding

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
                            NewsApplication.prefs!!.isDark = IS_DARK_MODE
                            R.style.DarkTheme
                        }
                        Configuration.UI_MODE_NIGHT_NO -> {
                            NewsApplication.prefs!!.isDark = NOT_DARK_MODE
                            R.style.AppTheme
                        }
                        else -> {
                            NewsApplication.prefs!!.isDark = IS_DARK_MODE
                            R.style.AppTheme
                        }


                    }
                }

            }


        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            bottomNavigation.setOnNavigationItemSelectedListener(
                onNavigationItemSelectedListener
            )

            ivThemeToggle.setOnClickListener {
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
                        0 -> toolbarTitle.text = TITLE_GENERAL
                        1 -> toolbarTitle.text = TITLE_BUSINESS
                        2 -> toolbarTitle.text = TITLE_TECHNOLOGY
                        else -> toolbarTitle.text = TITLE_GENERAL
                    }
                    bottomNavigation.menu.getItem(position).isChecked = true
                }
            })
        }
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
        NewsApplication.prefs!!.currentPage = binding.viewPager.currentItem
        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }

    private fun setUpThemeToggleImage() {
        binding.ivThemeToggle.apply {
            if (NewsApplication.prefs!!.isDark == IS_DARK_MODE) {
                setImageResource(R.drawable.ic_dark)
            } else {
                setImageResource(R.drawable.ic_light)
            }
        }
    }

    private fun setTitleText() {
        binding.toolbarTitle.text =
            when (binding.viewPager.currentItem) {
                0 -> TITLE_GENERAL
                1 -> TITLE_BUSINESS
                2 -> TITLE_TECHNOLOGY
                else -> throw UnsupportedOperationException()
            }

    }

    private fun setUpViewPager() {
        viewPagerAdapter = NewsCategoryAdapter(supportFragmentManager)
        viewPagerAdapter.apply {
            addFragment(ArticleFragment.newInstance(Category.GENERAL))
            addFragment(ArticleFragment.newInstance(Category.BUSINESS))
            addFragment(ArticleFragment.newInstance(Category.TECH))
        }
        val storedPageId = NewsApplication.prefs!!.currentPage
        binding.apply {
            viewPager.apply {
                adapter = viewPagerAdapter
                currentItem = storedPageId
            }
            bottomNavigation.selectedItemId = getSelectedItemId(storedPageId)
        }
    }

    private fun getSelectedItemId(pageId: Int) = when (pageId) {
        0 -> R.id.general
        1 -> R.id.science
        2 -> R.id.technology
        else -> R.id.general
    }


    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.general -> {
                    binding.viewPager.currentItem = 0
                    binding.toolbarTitle.text = TITLE_GENERAL
                    return@OnNavigationItemSelectedListener true
                }
                R.id.science -> {
                    binding.viewPager.currentItem = 1
                    binding.toolbarTitle.text = TITLE_BUSINESS
                    return@OnNavigationItemSelectedListener true
                }
                R.id.technology -> {
                    binding.viewPager.currentItem = 2
                    binding.toolbarTitle.text = TITLE_TECHNOLOGY
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
