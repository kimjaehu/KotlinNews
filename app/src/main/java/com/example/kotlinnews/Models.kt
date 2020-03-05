package com.example.kotlinnews

data class HomeFeed(val data: Data)
data class Data(val children: List<News>)
data class News(val data: Article)
data class Article(val title: String, val thumbnail: String, val url: String, val author: String, val subreddit: String, val created: Long)