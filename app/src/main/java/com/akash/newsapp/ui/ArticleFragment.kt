package com.akash.newsapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akash.newsapp.NewsApplication
import com.akash.newsapp.R
import com.akash.newsapp.base.EventObserver
import com.akash.newsapp.categoryconstants.Category
import com.akash.newsapp.databinding.ArticleListViewBinding
import com.akash.newsapp.internals.CustomTabsUtils
import com.akash.newsapp.utils.PreferenceHelper.Companion.IS_DARK_MODE
import com.akash.newsapp.viewmodels.ArticleViewModel
import com.akash.newsapp.viewmodels.ViewModelFactory
import javax.inject.Inject

class ArticleFragment : androidx.fragment.app.Fragment() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var binding: ArticleListViewBinding
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var category: String

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        /*getArticles in onAttach so that we will get article list only once.
        If getArticles invoked on onCreateView there will be one request every time when fragment is visible.*/
        (requireActivity().application as NewsApplication)
            .appComponent
            .injectArticleFragment(this)

        val bundle = arguments
        category = bundle?.getString(KEY_CATEGORY)!!
        articleViewModel = ViewModelProviders.of(this, factory)[ArticleViewModel::class.java]
        articleViewModel.getArticlesByCategory(category)
        articleViewModel.category = category
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.article_list_view, container, false)
        binding.vm = articleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        refreshLayout = binding.refreshLayout
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModelEvents()
        refreshLayout.setOnRefreshListener {
            articleViewModel.getArticlesByCategory(category, isFromSwipeRefresh = true)
        }
    }

    private fun observeViewModelEvents() {
        articleViewModel.event.observe(this, EventObserver {
            when (it) {
                is ArticleViewModel.ViewEvent.NavigateToBrowser -> CustomTabsUtils.launch(
                    activity!!,
                    it.url,
                    isDark()
                )
                is ArticleViewModel.ViewEvent.ShowToast -> {
                    Toast.makeText(activity!!, it.toastMessage, Toast.LENGTH_LONG).show()
                }
                is ArticleViewModel.ViewEvent.FinishRefresh -> {
                    refreshLayout.isRefreshing = false
                }
            }
        })
    }

    companion object {
        fun newInstance(@Category category: String): ArticleFragment {
            val fragment = ArticleFragment()
            val bundle = Bundle()
            bundle.putString(KEY_CATEGORY, category)
            fragment.arguments = bundle
            return fragment
        }

        private const val KEY_CATEGORY = "CATEGORY"
    }

    private fun isDark() = NewsApplication.prefs.isDark == IS_DARK_MODE

    fun scrollToTop() {
        if (::binding.isInitialized)
            binding.articleList.smoothScrollToPosition(0)
    }

}

