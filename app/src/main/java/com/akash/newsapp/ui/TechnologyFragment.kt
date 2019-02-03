package com.akash.newsapp.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.akash.newsapp.R
import com.akash.newsapp.adapters.ArticleListAdapter
import com.akash.newsapp.viewmodels.BusinessViewModel
import com.akash.newsapp.viewmodels.TechnologyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class TechnologyFragment : Fragment() {
    private val TAG = TechnologyFragment::class.java.simpleName
    private lateinit var noConnectionView : TextView
    private lateinit var articleListView : RecyclerView
    private lateinit var articleListAdapter: ArticleListAdapter
    private val technologyViewModel : TechnologyViewModel by viewModel()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        /*getArticles in onAttach so that we will get article list only once.
         If getArticles invoked on onCreateView there will be one request every time when fragment is visible.*/
        technologyViewModel.getArticles()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.article_list_view, container, false)
        articleListAdapter = ArticleListAdapter()
        noConnectionView = view.findViewById(R.id.no_connection)
        articleListView = view.findViewById(R.id.article_list)
        noConnectionView.text = "Technology Fragment"
        articleListView.layoutManager = LinearLayoutManager(activity)
        articleListView.adapter = articleListAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        technologyViewModel.articleList.observe(this, Observer {
            if(it?.size!! > 0){
                articleListAdapter.setData(it)
                noConnectionView.visibility = View.GONE
                for(article in it){
                    Log.e(TAG,"url : ${article.urlToImage}")
                }
            }
        })
        technologyViewModel.viewModelMessage.observe(this, Observer {
            if(!it.isNullOrEmpty()){
                Log.e(TAG,it)
                Toast.makeText(activity,"$it", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        fun newInstance(): GeneralFragment = GeneralFragment()
    }

}
