package com.akash.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.akash.newsapp.R
import com.akash.newsapp.base.BaseRowModel
import com.akash.newsapp.data.converters.DateConverters
import com.akash.newsapp.data.response.NewsArticle
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

/**
 * Created by Akash on 2019-09-07
 */
class ArticleRowViewModel(
    newsArticle: NewsArticle,
    private val viewModel: ArticleViewModel
) : BaseRowModel() {

    val source = MutableLiveData<String>().apply { value = newsArticle.source.name ?: "Unknown" }
    val title = MutableLiveData<String>().apply { value = newsArticle.title }
    val date = MutableLiveData<String>().apply { value = getDate(newsArticle.publishedAt) }
    val articleImageUrl = MutableLiveData<String>().apply { value = newsArticle.urlToImage }
    val url = newsArticle.url

    override fun setLayoutID() {
        layoutID = R.layout.article_list_item
    }

    private fun getDate(isoDate: String): String {
        val time: ZonedDateTime =
            DateConverters.getDate(isoDate).withZoneSameLocal(ZoneId.systemDefault())
        return "${time.dayOfMonth}/${time.monthValue}/${time.year}"
    }

    fun onRowClicked() {
        viewModel.openArticleInBrowser(this)
    }
}
