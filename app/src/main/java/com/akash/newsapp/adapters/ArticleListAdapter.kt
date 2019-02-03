package com.akash.newsapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.akash.newsapp.R
import com.akash.newsapp.data.converters.DateConverters
import com.akash.newsapp.data.response.NewsArticle
import com.bumptech.glide.Glide
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

class ArticleListAdapter : RecyclerView.Adapter<ArticleViewHolder>() {
    private var articleList: List<NewsArticle> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val source = if (!(articleList[position].source.name).isEmpty()) {
            articleList[position].source.name
        } else {
            "Unknown"
        }
        val urlToImage = articleList[position].urlToImage
        val title = articleList[position].title
        val date = getDate(articleList[position].publishedAt)
        holder.source.text = source
        holder.date.text = date
        holder.title.text = title
        Glide.with(holder.articleImage.context).load(urlToImage).into(holder.articleImage)
    }

    fun setData(articleList: List<NewsArticle>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    private fun getDate(isoDate: String): String {
        val time: ZonedDateTime = DateConverters.getDate(isoDate).withZoneSameLocal(ZoneId.systemDefault())
        var date = "${time.dayOfMonth}/${time.monthValue}/${time.year}"
        return date
    }
}

class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val source: TextView = view.findViewById(R.id.source)
    val title: TextView = view.findViewById(R.id.title)
    val articleImage: ImageView = view.findViewById(R.id.articleImage)
    val date: TextView = view.findViewById(R.id.publishedDate)
    val more: ImageView = view.findViewById(R.id.imgMore)

}
