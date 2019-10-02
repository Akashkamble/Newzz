package com.akash.newsapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.akash.newsapp.NewsApplication
import com.akash.newsapp.R
import com.akash.newsapp.base.EventObserver
import com.akash.newsapp.categoryconstants.Category
import com.akash.newsapp.databinding.ArticleListViewBinding
import com.akash.newsapp.internals.CustomTabsUtils
import com.akash.newsapp.viewmodels.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : androidx.fragment.app.Fragment() {
    private val TAG = ArticleFragment::class.java.simpleName

    private lateinit var noConnectionView: TextView
    private lateinit var articleListView: androidx.recyclerview.widget.RecyclerView
    private val articleViewModel: ArticleViewModel by viewModel()
    private lateinit var binding: ArticleListViewBinding
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var category: String

    override fun onAttach(context: Context) {
        super.onAttach(context)

        /*getArticles in onAttach so that we will get article list only once.
        If getArticles invoked on onCreateView there will be one request every time when fragment is visible.*/

        val bundle = arguments
        category = bundle?.getString(KEY_CATEGORY)!!
        articleViewModel.getArticlesByCategory(category)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.article_list_view, container, false)
        binding.vm = articleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        noConnectionView = binding.noConnection
        articleListView = binding.articleList
        refreshLayout = binding.refreshLayout
        noConnectionView.text = "General Fragment"
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
                    NewsApplication.prefs!!.isDark
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
}

