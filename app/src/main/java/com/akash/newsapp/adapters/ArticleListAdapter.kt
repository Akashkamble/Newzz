package com.akash.newsapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
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
        holder.bindData(articleList[position])
        setAnimation(holder.itemView,position)
    }

    fun setData(articleList: List<NewsArticle>) {
        this.articleList = articleList
        notifyDataSetChanged()
    }

    private var lastPosition: Int = -1

    private fun setAnimation(view: View, position: Int){
            val animation = AnimationUtils.loadAnimation(view.context,R.anim.item_animation_slide_from_right)
            view.startAnimation(animation)
            lastPosition = position
    }

    override fun onViewDetachedFromWindow(holder: ArticleViewHolder) {
        holder.clearAnimations()
        super.onViewDetachedFromWindow(holder)
    }

}

class ArticleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val TAG = ArticleViewHolder::class.java.simpleName
    private val source: TextView = view.findViewById(R.id.source)
    private val title: TextView = view.findViewById(R.id.title)
    private val articleImage: ImageView = view.findViewById(R.id.articleImage)
    private val date: TextView = view.findViewById(R.id.publishedDate)
//    val more: ImageView = view.findViewById(R.id.imgMore)

    fun bindData(newsArticle: NewsArticle) {
        source.text = if(!(newsArticle.source.name).isEmpty()) newsArticle.source.name else "Unknown"
        title.text = newsArticle.title
        date.text = getDate(newsArticle.publishedAt)
        Glide.with(articleImage.context).load(newsArticle.urlToImage).into(articleImage)
    }

    fun clearAnimations() {
        view.animation = null
    }

    private fun getDate(isoDate: String): String {
        val time: ZonedDateTime = DateConverters.getDate(isoDate).withZoneSameLocal(ZoneId.systemDefault())
        return "${time.dayOfMonth}/${time.monthValue}/${time.year}"
    }



}
