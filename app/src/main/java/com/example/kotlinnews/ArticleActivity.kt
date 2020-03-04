package com.example.kotlinnews

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.article_detail.view.*

class ArticleActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.article_detail)

        var navBarTitle = intent.getStringExtra(CustomViewHolder.ARTICLE_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        val articleURL = intent.getStringExtra(CustomViewHolder.ARTICLE_URL_KEY)

        webView = findViewById(R.id.webView_article)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl(articleURL)
    }
}