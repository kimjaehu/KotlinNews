package com.example.kotlinnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val articleTitles = listOf<String>("first", "second", "third", "fourth")

    override fun getItemCount(): Int {
        return articleTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.article_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val articleTitle = articleTitles.get(position)
        holder?.view?.textView_article_title?.text = articleTitle

    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}