package com.example.kotlinnews

class HomeFeed(val data: Data)

class Data(val children: List<News>)

class News(val data: Article)

class Article(val title: String, val thumbnail: String, val url: String, val author: String)