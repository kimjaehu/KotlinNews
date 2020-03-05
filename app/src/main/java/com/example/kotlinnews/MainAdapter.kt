package com.example.kotlinnews

import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return homeFeed.data.children.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val dateConversionFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val article = homeFeed.data.children.get(position).data
        holder?.view?.textView_article_title?.text = article.title
        val timestamp = article.created
        val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(timestamp))
        val date = LocalDate.parse(timestampAsDateString, dateConversionFormat)

        holder?.view?.textView_author?.text = "${date.month.toString().toLowerCase().capitalize()} ${date.dayOfMonth}, ${date.year} by ${article.author}"

        val thumbnailImageView = holder.view.imageView_thumbnail

        if(!TextUtils.isEmpty(article.thumbnail.trim()) && article.thumbnail.trim().isNotEmpty()){
            if(Patterns.WEB_URL.matcher(article.thumbnail).matches()){
                Picasso.get().load(article.thumbnail).into(thumbnailImageView)
            }
        } else {
            holder.view.imageView_thumbnail.visibility = View.GONE
        }

        holder?.article = article
    }


}

class CustomViewHolder(val view: View, var article: Article? = null): RecyclerView.ViewHolder(view) {

    companion object {
        val ARTICLE_TITLE_KEY = "ARTICLE_TITLE"
        val ARTICLE_THUMBNAIL_KEY = "ARTICLE_THUMBNAIL"
        val ARTICLE_URL_KEY = "ARTICLE_URL"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, ArticleActivity::class.java)
            intent.putExtra(ARTICLE_TITLE_KEY, article?.title)
            intent.putExtra(ARTICLE_THUMBNAIL_KEY, article?.thumbnail)
            intent.putExtra(ARTICLE_URL_KEY, article?.url)


            view.context.startActivity(intent)
        }
    }

}