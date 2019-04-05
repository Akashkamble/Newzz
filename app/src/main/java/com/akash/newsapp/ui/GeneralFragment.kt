package com.akash.newsapp.ui

import android.app.Activity
import androidx.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import com.akash.newsapp.R
import com.akash.newsapp.adapters.ArticleListAdapter
import com.akash.newsapp.internals.CustomTabsUtils
import com.akash.newsapp.viewmodels.GeneralViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GeneralFragment : androidx.fragment.app.Fragment(),ArticleListAdapter.ItemClickListener {
    private val TAG = GeneralFragment::class.java.simpleName

    private lateinit var noConnectionView : TextView
    private lateinit var articleListView : androidx.recyclerview.widget.RecyclerView
    private lateinit var articleListAdapter: ArticleListAdapter
    private val generalViewModel : GeneralViewModel by viewModel()
    private lateinit var layoutManager: androidx.recyclerview.widget.LinearLayoutManager


    override fun onAttach(context: Context) {
        super.onAttach(context)
        /*getArticles in onAttach so that we will get article list only once.
        If getArticles invoked on onCreateView there will be one request every time when fragment is visible.*/
        generalViewModel.getArticles()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.article_list_view, container, false)
        articleListAdapter = ArticleListAdapter(this@GeneralFragment)
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        noConnectionView = view.findViewById(R.id.no_connection)
        articleListView = view.findViewById(R.id.article_list)
        noConnectionView.text = "General Fragment"
        articleListView.layoutManager = layoutManager
        articleListView.adapter = articleListAdapter
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        generalViewModel.articleList.observe(this, Observer {
            if(it?.size!! > 0){
                articleListAdapter.setData(it)
                noConnectionView.visibility = View.GONE
            }
        })
        generalViewModel.viewModelMessage.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                Log.e(TAG,it)
                Toast.makeText(activity,"$it",Toast.LENGTH_SHORT).show()
            }
        })
    }


    override fun onItemClick(url: String) {
        CustomTabsUtils.launch(activity!!,url)
    }

    companion object {
        fun newInstance(): GeneralFragment = GeneralFragment()
    }
}

