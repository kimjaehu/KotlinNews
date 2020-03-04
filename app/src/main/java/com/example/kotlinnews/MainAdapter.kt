package com.example.kotlinnews

import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_row.view.*

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    val articleTitles = listOf<String>("first", "second", "third", "fourth")

    override fun getItemCount(): Int {
        return homeFeed.data.children.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val article = homeFeed.data.children.get(position)
        holder?.view?.textView_article_title?.text = article.data.title
        holder?.view?.textView_author?.text = "by ${article.data.author}"

        val thumbnailImageView = holder.view.imageView_thumbnail

        if(!TextUtils.isEmpty(article.data.thumbnail.trim()) && article.data.thumbnail.trim().isNotEmpty()){
            if(Patterns.WEB_URL.matcher(article.data.thumbnail).matches()){
                Picasso.get().load(article.data.thumbnail).into(thumbnailImageView)
            }
        } else {
            holder.view.imageView_thumbnail.visibility = View.GONE
        }



    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}